package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;

public interface EmployeesService {

    GetEmployeesResponse findAll();
}