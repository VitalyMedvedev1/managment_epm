package ru.homework.andry.soap.service;

import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.service.EmployeeDataValidation;
import lombok.extern.slf4j.*;
import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class EmployeeDataValidationImpl implements EmployeeDataValidation {

    @Override
    public void validate(List<EmployeeElement> employees) {
        log.info("Start validate employees data");
        checkEmployeesDataAndSetError(employees);
    }

    private void checkEmployeesDataAndSetError(List<EmployeeElement> employees) {
        log.debug("Start checking data employees");
        employees.stream()
                .map(this::incorrectSalaryAndRequiredField)
                .map(this::incorrectSalary)
                .map(this::incorrectRequiredField)
                .collect(Collectors.toList());
    }

    private EmployeeElement incorrectSalaryAndRequiredField(EmployeeElement emp) {
        if (!emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
            emp.setErrorRequiredMessage();
        }
        return emp;
    }

    private EmployeeElement incorrectRequiredField(EmployeeElement emp) {
        if (emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorRequiredMessage();
        }
        return emp;
    }

    private EmployeeElement incorrectSalary(EmployeeElement emp) {
        if (!emp.checkSalary() && !emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
        }
        return emp;
    }
}
