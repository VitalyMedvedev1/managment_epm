package ru.homework.andry.soap.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.validation.EmployeeValidation;
import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
@RequiredArgsConstructor
public class EmployeeValidationImpl implements EmployeeValidation {

    @Override
    public List<EmployeeElement> validate(List<EmployeeElement> employees) {
        log.info("Start validate employees data");
        List<EmployeeElement> validatedElements = checkEmployeesDataAndSetError(employees);
        log.info("End validate request employees");
        return validatedElements;
    }

    private List<EmployeeElement> checkEmployeesDataAndSetError(List<EmployeeElement> employees) {
        log.debug("Start checking data employees");
        return employees.stream()
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
