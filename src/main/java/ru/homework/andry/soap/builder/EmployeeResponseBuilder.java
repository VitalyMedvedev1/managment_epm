package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.Employee;
import ru.homework.andry.soap.model.AbstractEmployee;

import java.util.List;

public interface EmployeeResponseBuilder<T> {
    T build(T response, List<AbstractEmployee> employees);
}