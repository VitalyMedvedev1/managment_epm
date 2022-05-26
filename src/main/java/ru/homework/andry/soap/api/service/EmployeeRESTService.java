package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;

public interface EmployeeRESTService {

    List<EmployeeElement> findAll();

    List<EmployeeElement> findAllByPosition(String position);

    List<EmployeeElement> saveAll(List<EmployeeElement> entities);

}
