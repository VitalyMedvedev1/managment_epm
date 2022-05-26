package ru.homework.andry.soap.builder.impl;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import lombok.extern.slf4j.Slf4j;
import ru.homework.andry.soap.service.impl.EmployeeMapperService;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEmployeeResponseBuilder implements EmployeeResponseBuilder<GetEmployeesResponse> {

    private final EmployeeMapperService employeeMapperService;

    @Override  //todo в проекте нет GetEmployeesResponse
    public void build(GetEmployeesResponse getEmployeesResponse, List<AbstractEmployee> employees) {
        log.info("Start generate GetEmployeesResponse");
        getEmployeesResponse.getEmployees()
                .addAll(employeeMapperService.elementsToEmployees(employees));

        getEmployeesResponse.setStatus(StatusResponseBuilder.build());
    }
}
