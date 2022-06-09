package ru.homework.andry.soap.api.builder;

import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;

import java.util.List;

public interface EmployeeSoapResponseBuilder {

    GetEmployeesResponse buildGetEmployeesResponse (List<Employee> employees);

    CreateEmployeesResponse buildCreateEmployeesResponse (List<Employee> employees);
}