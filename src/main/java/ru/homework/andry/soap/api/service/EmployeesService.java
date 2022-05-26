package ru.homework.andry.soap.api.service;

import io.dliga.micro.employee_web_service.CreateEmployeesRequest;
import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;

public interface EmployeesService {

    GetEmployeesResponse findAll();

    CreateEmployeesResponse saveAll(CreateEmployeesRequest request);
}