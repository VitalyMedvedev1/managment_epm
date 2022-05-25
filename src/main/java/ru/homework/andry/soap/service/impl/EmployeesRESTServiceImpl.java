package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.exeption.BusinessLogicException;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.employee.AbstractEmployee;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.service.AbstractEmployeeService;
import ru.homework.andry.soap.service.EmployeeRESTService;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service("REST")
@RequiredArgsConstructor
@Slf4j
public class EmployeesRESTServiceImpl extends AbstractEmployeeService implements EmployeeRESTService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<AbstractEmployee> findAll() {
        log.info("Find all employees");
        List<EmployeeEntity> entities = employeeRepository.findAll();
        if (entities.isEmpty()) {
            throw new BusinessLogicException("Elements by position {0} did not find");
        }
        return employeeMapper.entityToElement(entities);
    }

    @Override
    public List<AbstractEmployee> findAllByPosition(String positionValue) {
        Position position = getEnumPosition(positionValue);
        log.info("Find employees by position: {}", position.name());
        List<EmployeeEntity> entities = employeeRepository.findAllByPosition(position);

        if (entities.isEmpty()) {
            throw new BusinessLogicException(
                    MessageFormat.format("Elements by position {0} did not find", position.name()));
        }
        return employeeMapper.entityToElement(entities);
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
    public List<AbstractEmployee> saveAll(List<AbstractEmployee> entities) {
        return null;
    }
}
