package ru.homework.andry.soap.conroller;

import io.dliga.micro.employee_web_service.CreateEmployeesRequest;
import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.homework.andry.soap.api.service.EmployeesService;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://dliga.io/micro/employee-web-service";
    private static final String LOCAL_GET_EMPLOYEE_PART = "getEmployeesRequest";
    private static final String LOCAL_CREATE_EMPLOYEE_PART = "createEmployeesRequest";
    private final EmployeesService employeesService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_GET_EMPLOYEE_PART)
    @ResponsePayload
    public GetEmployeesResponse find() {
        return employeesService.findAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_CREATE_EMPLOYEE_PART)
    @ResponsePayload
    public CreateEmployeesResponse save(@RequestPayload CreateEmployeesRequest request) {
        return employeesService.saveAll(request);
    }

}