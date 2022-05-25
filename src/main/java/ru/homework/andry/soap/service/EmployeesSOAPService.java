package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.CreateEmployeesRequest;
import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;

public interface EmployeesSOAPService {

    GetEmployeesResponse findAll();

    CreateEmployeesResponse saveAll(CreateEmployeesRequest request);
}