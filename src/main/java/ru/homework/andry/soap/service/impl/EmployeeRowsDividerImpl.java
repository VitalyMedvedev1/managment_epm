package ru.homework.andry.soap.service.impl;

import org.springframework.stereotype.Component;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.service.EmployeeRowsDivider;
import lombok.extern.slf4j.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmployeeRowsDividerImpl implements EmployeeRowsDivider {

    @Override
    public Map<Boolean, List<AbstractEmployee>> divideOnCorrectAndIncorrect(List<AbstractEmployee> employees) {
        log.info("Start divide employees data from soap message");
        Map<Boolean, List<AbstractEmployee>> employeesDivideByInputFieldData = new HashMap<>();

        employeesDivideByInputFieldData.put(true, getCorrectRowEmployees(employees));
        employeesDivideByInputFieldData.put(false, getIncorrectRowEmployees(employees));

        return employeesDivideByInputFieldData;
    }

    private List<AbstractEmployee> getIncorrectRowEmployees(List<AbstractEmployee> employees) {
        log.debug("Start to collect incorrect employees data from soap message");
        return employees.stream()
                .filter(emp -> !emp.checkSalary() || !emp.checkRequiredField())
                .peek(emp -> {
                    if (!emp.checkSalary() && !emp.checkRequiredField()) {
                        emp.setErrorIncorrectSalaryMessage();
                        emp.setErrorRequiredMessage();
                    }
                })
                .peek(emp -> {
                    if (!emp.checkSalary() && emp.checkRequiredField()) {
                        emp.setErrorIncorrectSalaryMessage();
                    }
                })
                .peek(emp -> {
                    if (emp.checkSalary() && !emp.checkRequiredField()) {
                        emp.setErrorRequiredMessage();
                    }
                })
                .collect(Collectors.toList());
    }

    private List<AbstractEmployee> getCorrectRowEmployees(List<AbstractEmployee> employees) {
        log.debug("Start to collect correct employees data from soap message");
        return employees.stream()
                .filter(AbstractEmployee::checkSalary)
                .filter(AbstractEmployee::checkRequiredField)
                .collect(Collectors.toList());
    }
}
