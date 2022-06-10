package ru.homework.andry.soap.api.validation;

import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;

public interface EmployeeValidation {

    List<EmployeeElement> validate(List<EmployeeElement> employees);

}
