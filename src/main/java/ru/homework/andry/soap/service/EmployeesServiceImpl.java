package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.api.service.EmployeeService;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.exeption.BusinessLogicException;
import ru.homework.andry.soap.mapper.EmployeeSwitcherMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl extends AbstractEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSwitcherMapper employeeMapperService;

    @Override
    public List<Employee> findAll() {
        log.info("Find all employees");
        List<EmployeeEntity> entities = employeeRepository.findAll();
        if (entities.isEmpty()) {
            throw new BusinessLogicException("Elements by position {0} did not find");
        }
        return null;
    }

    @Override
    public List<Employee> findAllByPosition(String positionValue) {
        Position position = getEnumPosition(positionValue);
        log.info("Find employees by position: {}", position.name());
        List<EmployeeEntity> entities = employeeRepository.findAllByPosition(position);

        if (entities.isEmpty()) {
            throw new BusinessLogicException(
                    MessageFormat.format("Elements by position {0} did not find", position.name()));
        }
        return null;
    }

    private Position getEnumPosition(String positionValue) {
        try {
            return Position.fromValue(positionValue.toLowerCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new BusinessLogicException(
                    MessageFormat.format(
                            "Position must be in: {0}",
                            Arrays.stream(Position.values())
                                    .collect(Collectors.toList())));
        }
    }

    @Override
    public List<Employee> saveAll(List<EmployeeElement> entities) {
        return null;
    }
}
