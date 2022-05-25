package ru.homework.andry.soap.service;

import org.apache.commons.lang3.StringUtils;
import ru.homework.andry.soap.model.employee.AbstractEmployee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractEmployeeService {

    protected List<AbstractEmployee> getCorrectEmployee(List<AbstractEmployee> abstractEmployees) {
        log.info("Get correct employees");
        return abstractEmployees.stream()
                .filter(employee -> StringUtils.isBlank(employee.getErrorMessage()))
                .collect(Collectors.toList());
    }
}