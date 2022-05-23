package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Status;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;

import java.util.List;

import static ru.homework.andry.soap.constant.Values.ERROR_CODE;

@Component
@RequiredArgsConstructor
public class CreateEmployeeResponseBuilder implements EmployeeResponseBuilder<CreateEmployeesResponse> {

    private final EmployeeMapper employeeMapper;

    @Override
    public CreateEmployeesResponse build(CreateEmployeesResponse response, List<AbstractEmployee> employees) {
        employees.forEach(
                element -> {
                    Employee employee = employeeMapper.elementToEmployeeResponse(element);
                    employee.setStatus(getResponseStatus(element.getErrorMessage()));
                    response.getEmployees().add(employee);
                });
        return response;
    }

    private Status getResponseStatus(String errorMessage) {
        if (StringUtils.isNotBlank(errorMessage)) {
            return ResponseStatusBuilder.build(ERROR_CODE, errorMessage);
        } else {
            return ResponseStatusBuilder.build();
        }
    }

/*
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
    }*/
}
