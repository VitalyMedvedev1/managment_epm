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
        log.debug("Start to collect incorrect employees data from soap message");
        return employees.stream()
                .peek(this::incorrectSalaryAndRequiredField)
                .peek(this::incorrectSalary)
                .peek(this::incorrectRequiredField)
                .collect(Collectors.toList());
    }

    private void incorrectRequiredField(AbstractEmployee emp) {
        if (emp.checkSalary() && !emp.checkRequiredField()) {
            emp.setErrorRequiredMessage();
        }
    }

    private void incorrectSalary(AbstractEmployee emp) {
        if (!emp.checkSalary() && emp.checkRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
        }
    }

    private void incorrectSalaryAndRequiredField(AbstractEmployee emp) {
        if (!emp.checkSalary() && !emp.checkRequiredField()) {
            emp.setErrorIncorrectSalaryMessage();
            emp.setErrorRequiredMessage();
        }
    }

}
