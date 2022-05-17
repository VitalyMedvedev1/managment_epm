package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetEmployeeResponseBuilder {

    public GetEmployeesResponse build(List<Employee> employees) {
        GetEmployeesResponse response = new GetEmployeesResponse();
        response.getEmployees().addAll(employees);
        response.setStatus(ResponseStatusBuilder.build());
        return response;
    }

    public GetEmployeesResponse build(int errorCode, String errorMessage) {
        GetEmployeesResponse response = new GetEmployeesResponse();
        response.setStatus(ResponseStatusBuilder.build(errorCode, errorMessage));
        return response;
    }
}
