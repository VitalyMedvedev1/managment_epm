package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.api.service.EmployeeDataValidation;
import ru.homework.andry.soap.api.service.EmployeeService;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.exeption.BusinessLogicException;
import ru.homework.andry.soap.mapper.EmployeeSwitcherMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeesServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSwitcherMapper employeeSwitcherMapper;
    private final EmployeeDataValidation employeeDataValidation;

    @Override
    public List<Employee> find() {
        log.info("Start finding employees");
        List<EmployeeEntity> entities = employeeRepository.findAll();
        if (entities.isEmpty()) {
            throw new BusinessLogicException("Employees didn't find");
        }

        List<EmployeeElement> elements = employeeSwitcherMapper.entityToElement(entities);

        log.info("End finding employees");
        List<Employee> employees = employeeSwitcherMapper.elementsToEmployees(elements);
        return employees;
    }

    @Override
    public List<Employee> findByPosition(String positionValue) {
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
        log.info("Start create employees");
        List<EmployeeElement> requestElements = employeeSwitcherMapper.employeesToElements(employees);

        List<EmployeeElement> validatedElements = employeeDataValidation.validate(requestElements);

        List<EmployeeElement> correctElements = getCorrectEmployees(validatedElements);

        List<EmployeeEntity> entities =
                employeeRepository.saveAll(employeeSwitcherMapper.elementsToEntities(correctElements));

        log.info("Successful created correct employees with ids: {}", getEntityIds(entities));
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

        List<EmployeeElement> validatedElements = employeeDataValidation.validate(requestElements);

        List<EmployeeElement> correctElements = getCorrectEmployees(validatedElements);

        employeeRepository.saveAll(employeeSwitcherMapper.elementsToEntities(correctElements));

        log.info("Successful update employees with ids: {}", getElementIds(correctElements));
        return employeeSwitcherMapper.elementsToEmployees(validatedElements);
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
