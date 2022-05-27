package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.AbstractEmployee;

import java.util.List;

public interface EmployeeDataValidation {

    List<AbstractEmployee> validate(List<AbstractEmployee> employees);

}
