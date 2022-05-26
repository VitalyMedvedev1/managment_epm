package ru.homework.andry.soap.service;

import org.apache.commons.lang3.StringUtils;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractEmployeeService {

    protected List<EmployeeElement> getCorrectEmployee(List<EmployeeElement> employeeElements) {
        log.info("Get correct employees");
        return employeeElements.stream()
                .filter(employee -> StringUtils.isBlank(employee.getErrorMessage()))
                .collect(Collectors.toList());
    }
}