package ru.homework.andry.soap.service;

import ru.homework.andry.soap.model.AbstractEmployee;

import java.util.List;

public interface EmployeeDataValidation {

    List<AbstractEmployee> validate(List<AbstractEmployee> employees);

}
