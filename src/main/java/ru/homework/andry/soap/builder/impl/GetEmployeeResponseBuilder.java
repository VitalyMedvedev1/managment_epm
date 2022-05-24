package ru.homework.andry.soap.builder.impl;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEmployeeResponseBuilder implements EmployeeResponseBuilder<GetEmployeesResponse> {

    private final EmployeeMapper employeeMapper;

    @Override
    public void build(GetEmployeesResponse getEmployeesResponse, List<AbstractEmployee> employees) {
        log.info("Start generate GetEmployeesResponse");
        getEmployeesResponse.getEmployees()
                .addAll(employeeMapper.elementsToEmployees(employees));

        getEmployeesResponse.setStatus(StatusResponseBuilder.build());
    }
}
