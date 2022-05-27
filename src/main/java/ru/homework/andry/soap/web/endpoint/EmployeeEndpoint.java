package ru.homework.andry.soap.web.endpoint;

import io.dliga.micro.employee_web_service.CreateEmployeesRequest;
import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://dliga.io/micro/employee-web-service";
    private static final String LOCAL_GET_EMPLOYEE_PART = "getEmployeesRequest";
    private static final String LOCAL_CREATE_EMPLOYEE_PART = "createEmployeesRequest";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_GET_EMPLOYEE_PART)
    @ResponsePayload
    public GetEmployeesResponse find() {
//        return employeesSOAPService.findAll();
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_CREATE_EMPLOYEE_PART)
    @ResponsePayload
    public CreateEmployeesResponse save(@RequestPayload CreateEmployeesRequest request) {
//        return employeesSOAPService.saveAll(request);
        return null;
    }

}