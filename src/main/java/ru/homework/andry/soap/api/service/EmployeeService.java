package ru.homework.andry.soap.api.service;

import io.dliga.micro.employee_web_service.Employee;
import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;

public interface EmployeeService{

    List<Employee> findAll();

    List<Employee> findAllByPosition(String position);

    List<Employee> saveAll(List<EmployeeElement> entities);

}