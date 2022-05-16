package ru.homework.andry.soap.conroller;

import io.dliga.micro.employee_web_service.GetEmployeesRequest;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.homework.andry.soap.service.EmployeesService;

import javax.xml.bind.JAXBElement;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://dliga.io/micro/employee-web-service";
    private final EmployeesService employeesService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeesRequest")
    @ResponsePayload
    public GetEmployeesResponse getEmployees(@RequestPayload JAXBElement<GetEmployeesRequest> request) {
        return employeesService.findAll();
    }
}