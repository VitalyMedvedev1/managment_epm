package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.element.EmployeeElement;
import lombok.extern.slf4j.Slf4j;
import ru.homework.andry.soap.service.EmployeeMapperServiceImpl;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEmployeeResponseBuilder implements EmployeeResponseBuilder<GetEmployeesResponse> { //todo плохое название билдера. лучше так EmployeeResponseBuilder

    private final EmployeeMapperServiceImpl employeeMapperServiceImpl;

    @Override
    public void build(GetEmployeesResponse getEmployeesResponse, List<EmployeeElement> employees) {
        log.info("Start generate GetEmployeesResponse");
        getEmployeesResponse.getEmployees()
                .addAll(employeeMapperServiceImpl.elementsToEmployees(employees));

        getEmployeesResponse.setStatus(StatusResponseBuilder.build());
    }
}
