package ru.homework.andry.soap.service;

import ru.homework.andry.soap.model.employee.AbstractEmployee;

import java.util.List;

public interface EmployeeRESTService {

    List<AbstractEmployee> findAll();

    List<AbstractEmployee> findAllByPosition(String position);
}
