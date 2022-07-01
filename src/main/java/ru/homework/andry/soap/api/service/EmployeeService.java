package ru.homework.andry.soap.api.service;

import io.dliga.micro.employee_web_service.Employee;

import java.util.List;

public interface EmployeeService{

    List<Employee> findAll();

    List<Employee> findAllByPosition(String position);

    List<Employee> create(List<Employee> employees);

    List<Employee> update(List<Employee> employees);

    void delete(List<Long> requestIds);

    byte[] getForm(String uuid);
}