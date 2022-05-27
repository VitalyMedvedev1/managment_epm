package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.EmployeeElement;

import java.util.List;

public interface EmployeeDataValidation {

    List<EmployeeElement> validate(List<EmployeeElement> employees);

}
