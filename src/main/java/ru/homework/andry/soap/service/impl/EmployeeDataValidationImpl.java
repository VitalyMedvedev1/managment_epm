package ru.homework.andry.soap.service.impl;

import org.springframework.stereotype.Component;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.service.EmployeeDataValidation;
import lombok.extern.slf4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmployeeDataValidationImpl implements EmployeeDataValidation {

    @Override
    public List<AbstractEmployee> validate(List<AbstractEmployee> employees) {
        log.info("Start validate employees data");
        return checkEmployeesDataAndSetError(employees);
    }

    private List<AbstractEmployee> checkEmployeesDataAndSetError(List<AbstractEmployee> employees) {
        log.debug("Start checking data employees");
        return employees.stream()
                .peek(this::incorrectSalaryAndRequiredField)
                .peek(this::incorrectSalary)
                .peek(this::incorrectRequiredField)
                .collect(Collectors.toList());
    }

    private void incorrectSalaryAndRequiredField(AbstractEmployee emp) {
        if (!emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
            emp.setErrorRequiredMessage();
        }
    }

    private void incorrectRequiredField(AbstractEmployee emp) {
        if (emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorRequiredMessage();
        }
    }

    private void incorrectSalary(AbstractEmployee emp) {
        if (!emp.checkSalary() && !emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
        }
    }
}
