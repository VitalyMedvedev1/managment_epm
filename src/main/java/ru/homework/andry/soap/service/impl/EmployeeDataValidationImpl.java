package ru.homework.andry.soap.service.impl;

import org.springframework.stereotype.Component;
import ru.homework.andry.soap.model.employee.AbstractEmployee;
import ru.homework.andry.soap.service.EmployeeDataValidation;
import lombok.extern.slf4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmployeeDataValidationImpl implements EmployeeDataValidation {

    @Override
    public void validate(List<AbstractEmployee> employees) {
        log.info("Start validate employees data");
        checkEmployeesDataAndSetError(employees);
    }

    private void checkEmployeesDataAndSetError(List<AbstractEmployee> employees) {
        log.debug("Start checking data employees");
        employees.stream()
                .map(this::incorrectSalaryAndRequiredField)
                .map(this::incorrectSalary)
                .map(this::incorrectRequiredField)
                .collect(Collectors.toList());
    }

    private AbstractEmployee incorrectSalaryAndRequiredField(AbstractEmployee emp) {
        if (!emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
            emp.setErrorRequiredMessage();
        }
        return emp;
    }

    private AbstractEmployee incorrectRequiredField(AbstractEmployee emp) {
        if (emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorRequiredMessage();
        }
        return emp;
    }

    private AbstractEmployee incorrectSalary(AbstractEmployee emp) {
        if (!emp.checkSalary() && !emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
        }
        return emp;
    }
}
