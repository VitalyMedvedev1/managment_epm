package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.employee.AbstractEmployee;

import java.util.List;

public interface EmployeeRESTService {

    List<AbstractEmployee> findAll();

    List<AbstractEmployee> findAllByPosition(String position);

    List<AbstractEmployee> saveAll(List<AbstractEmployee> entities);

}
