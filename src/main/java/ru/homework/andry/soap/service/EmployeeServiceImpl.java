package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.api.kafka.EmployeeSender;
import ru.homework.andry.soap.api.service.EmployeeService;
import ru.homework.andry.soap.api.validation.EmployeeValidation;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.entity.TaskEntity;
import ru.homework.andry.soap.exeption.BusinessLogicException;
import ru.homework.andry.soap.mapper.EmployeeSwitcherMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.util.PdfGenerator;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSwitcherMapper employeeSwitcherMapper;
    private final EmployeeValidation employeeValidation;
    private final EmployeeSender employeeSender;
    private final PdfGenerator pdfGenerator;

    private static final String templateName = "employeeProfile";

    @Override
    public List<Employee> findAll() {
        log.info("Start finding employees");
        List<EmployeeEntity> entities = employeeRepository.findAll();
        if (entities.isEmpty()) {
            throw new BusinessLogicException("Employees didn't find");
        }

        List<EmployeeElement> elements = employeeSwitcherMapper.entityToElement(entities);

        log.info("End finding employees");
        return employeeSwitcherMapper.elementsToEmployees(elements);
    }

    @Override
    public List<Employee> findAllByPosition(String positionValue) {
        Position position = getEnumPosition(positionValue);
        log.info("Start finding employees by position: {}", position.name());
        List<EmployeeEntity> entities = employeeRepository.findAllByPosition(position);

        if (entities.isEmpty()) {
            throw new BusinessLogicException(
                    MessageFormat.format("Employees by position: {0} didn't find", position.name()));
        }

        List<EmployeeElement> elements = employeeSwitcherMapper.entityToElement(entities);

        return employeeSwitcherMapper.elementsToEmployees(elements);
    }

    private Position getEnumPosition(String positionValue) {
        log.debug("Find position by value: {}", positionValue);
        try {
            return Position.fromValue(positionValue.toLowerCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new BusinessLogicException(MessageFormat.format("Position must be in: {0}",
                                                                  Arrays.stream(Position.values())
                                                                        .collect(Collectors.toList())));
        }
    }

    @Override
    public List<Employee> create(List<Employee> employees) {
        log.info("Start REST request to create employees");
        List<EmployeeElement> requestElements = employeeSwitcherMapper.employeesToElements(employees);

        List<EmployeeElement> validatedElements = employeeValidation.validate(requestElements);

        List<EmployeeElement> correctElements = getCorrectEmployees(validatedElements);

        List<EmployeeEntity> entities = employeeSwitcherMapper.elementsToEntities(correctElements);

        if (!entities.isEmpty()) {
            log.info("Put REST request create employees: {} to kafka", entities);
            employeeSender.sendToCreate(entities);
        }

        return employeeSwitcherMapper.elementsToEmployees(validatedElements);
    }

    @Override
    public List<Employee> update(List<Employee> employees) {
        log.info("Start update employees");
        List<EmployeeElement> requestElements = employeeSwitcherMapper.employeesToElements(employees);

        List<Long> requestIds = getElementIds(requestElements);

        List<EmployeeEntity> entities = employeeRepository.findAllById(requestIds);

        List<Long> foundEntityIds = getEntityIds(entities);

        if (!foundEntityIds.containsAll(requestIds)) {
            throw new EntityNotFoundException("Employees didn't found");
        }

        List<EmployeeElement> validatedElements = employeeValidation.validate(requestElements);

        List<EmployeeElement> correctElements = getCorrectEmployees(validatedElements);

        employeeRepository.saveAll(employeeSwitcherMapper.elementsToEntities(correctElements));

        log.info("Successful update employees with ids: {}", getElementIds(correctElements));
        return employeeSwitcherMapper.elementsToEmployees(validatedElements);
    }

    @Override
    public void delete(List<Long> requestIds) {
        log.info("Start delete employees with ids: {}", requestIds);

        List<EmployeeEntity> entities = employeeRepository.findAllById(requestIds);

        List<Long> foundEntityIds = getEntityIds(entities);

        if (!foundEntityIds.containsAll(requestIds)) {
            throw new EntityNotFoundException("Employees didn't found");
        }

        log.info("Put REST request delete employee with ids: {} to kafka", requestIds);
        employeeSender.sendToDelete(requestIds);
    }

    @Override
    public byte[] getForm(String uuid) {
        log.info("Start load form to employee with uuid: {}", uuid);
        EmployeeEntity entity = employeeRepository.findByUuid(uuid)
                                                  .orElseThrow(() -> new EntityNotFoundException(
                                                          MessageFormat.format("Employee with uuid: {0} didn't find", uuid)));

        byte[] generate = pdfGenerator.generate(templateName, getParameters(entity));
        return generate;
    }

    private Map<String, Object> getParameters(EmployeeEntity entity) {
        return Map.ofEntries(Map.entry("name", entity.getFirstName()),
                             Map.entry("surname", entity.getLastName()),
                             Map.entry("age", entity.getAge()),
                             Map.entry("salary", entity.getSalary()),
                             Map.entry("level", entity.getLevel()),
                             Map.entry("language", entity.getLanguage()),
                             Map.entry("type", entity.getType()),
                             Map.entry("project", entity.getProject()),
                             Map.entry("uuid", entity.getUuid()),
                             Map.entry("position", entity.getPosition()),
                             Map.entry("tasks",
                                       entity.getTasks()
                                             .stream()
                                             .map(TaskEntity::getDescription)
                                             .collect(Collectors.toList())));
    }

    private List<Long> getEntityIds(List<EmployeeEntity> entities) {
        return entities.stream()
                       .map(EmployeeEntity::getId)
                       .collect(Collectors.toList());
    }

    private List<Long> getElementIds(List<EmployeeElement> elements) {
        return elements.stream()
                       .map(EmployeeElement::getId)
                       .collect(Collectors.toList());
    }

    private List<EmployeeElement> getCorrectEmployees(List<EmployeeElement> employeeElements) {
        log.debug("Get correct employees");
        return employeeElements.stream()
                               .filter(employee -> StringUtils.isBlank(employee.getErrorMessage()))
                               .collect(Collectors.toList());
    }
}
