package ru.homework.andry.soap.api.service;

import io.dliga.micro.employee_web_service.Employee;
import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;

public interface EmployeeServiceDecorator {
    <T> T findAll();

    <T> T findAllByPosition(String position);

    <T> T saveAll(List<Employee> entities);

}
