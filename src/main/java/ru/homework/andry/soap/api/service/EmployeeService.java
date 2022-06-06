package ru.homework.andry.soap.api.service;

import io.dliga.micro.employee_web_service.Employee;

import java.util.List;

public interface EmployeeService{

    List<Employee> find();

    List<Employee> findByPosition(String position);

    List<Employee> create(List<Employee> employees);

    List<Employee> update(List<Employee> employees);

}