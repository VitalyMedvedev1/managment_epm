package ru.homework.andry.soap.conroller;

import io.dliga.micro.employee_web_service.CreateEmployeesRequest;
import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.homework.andry.soap.service.EmployeesService;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://dliga.io/micro/employee-web-service";
    private final EmployeesService employeesService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest")
    @ResponsePayload
    public GetEmployeesResponse find() {
        return employeesService.findAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEmployeesRequest")
    @ResponsePayload
    public CreateEmployeesResponse save(@RequestPayload CreateEmployeesRequest request) {
        return employeesService.saveAll(request);
    }
}