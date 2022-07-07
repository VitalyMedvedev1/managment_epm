package ru.homework.andry.soap.api.service;

import io.dliga.micro.employee_web_service.Employee;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findAllByPosition(String position);

    List<Employee> create(List<Employee> employees);

    List<Employee> update(List<Employee> employees);

    void delete(List<Long> requestIds);

    ResponseEntity<Resource> getForm(String uuid);
}