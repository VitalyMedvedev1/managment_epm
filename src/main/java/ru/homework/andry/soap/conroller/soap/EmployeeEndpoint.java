package ru.homework.andry.soap.conroller.soap;

import io.dliga.micro.employee_web_service.CreateEmployeesRequest;
import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.homework.andry.soap.service.EmployeesSOAPService;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://dliga.io/micro/employee-web-service";
    private static final String LOCAL_GET_EMPLOYEE_PART = "getEmployeesRequest";
    private static final String LOCAL_CREATE_EMPLOYEE_PART = "createEmployeesRequest";
    private final EmployeesSOAPService employeesSOAPService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_GET_EMPLOYEE_PART)
    @ResponsePayload
    public GetEmployeesResponse find() {
        return employeesSOAPService.findAll();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = LOCAL_CREATE_EMPLOYEE_PART)
    @ResponsePayload
    public CreateEmployeesResponse save(@RequestPayload CreateEmployeesRequest request) {
        return employeesSOAPService.saveAll(request);
    }
}