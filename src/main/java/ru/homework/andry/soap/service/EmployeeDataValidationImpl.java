package ru.homework.andry.soap.service;

import org.springframework.stereotype.Component;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.api.service.EmployeeDataValidation;
import lombok.extern.slf4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmployeeDataValidationImpl implements EmployeeDataValidation {

    @Override
    public List<EmployeeElement> validate(List<EmployeeElement> employees) {
        log.info("Start validate employees data");
        return checkEmployeesDataAndSetError(employees);
    }

    private List<EmployeeElement> checkEmployeesDataAndSetError(List<EmployeeElement> employees) {
        log.debug("Start checking data employees");
        return employees.stream()
                .peek(this::incorrectSalaryAndRequiredField)
                .peek(this::incorrectSalary)
                .peek(this::incorrectRequiredField)
                .collect(Collectors.toList());
    }

    private void incorrectSalaryAndRequiredField(EmployeeElement emp) {
        if (!emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
            emp.setErrorRequiredMessage();
        }
    }

    private void incorrectRequiredField(EmployeeElement emp) {
        if (emp.checkSalary() && emp.isBlankRequiredField()) {
            emp.setErrorRequiredMessage();
        }
    }

    private void incorrectSalary(EmployeeElement emp) {
        if (!emp.checkSalary() && !emp.isBlankRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
        }
    }
}
