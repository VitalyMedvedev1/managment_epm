package ru.homework.andry.soap.builder.impl;

import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Status;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static ru.homework.andry.soap.constant.ValueConst.ERROR_CODE;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateEmployeeResponseBuilder implements EmployeeResponseBuilder<CreateEmployeesResponse> {

    private final EmployeeMapper employeeMapper;

    @Override //todo в проекте нет CreateEmployeesResponse Employee Status
    public void build(CreateEmployeesResponse createEmployeesResponse, List<AbstractEmployee> employees) {
        log.info("Start generate GetEmployeesResponse");
        employees.forEach(
                element -> {
                    Employee employee = employeeMapper.elementToEmployeeResponse(element);
                    employee.setStatus(getResponseStatus(element.getErrorMessage()));
                    createEmployeesResponse.getEmployees().add(employee);
                });
    }

    private Status getResponseStatus(String errorMessage) {
        log.debug("Start generate StatusResponse");
        if (StringUtils.isNotBlank(errorMessage)) {
            return StatusResponseBuilder.build(ERROR_CODE, errorMessage);
        } else {
            return StatusResponseBuilder.build();
        }
    }
}
