package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.employee.EmployeeElement;

import java.util.List;

public interface EmployeeDataValidation {
    //todo все интерфейсы в отдельный пакет api
    // done

    void validate(List<EmployeeElement> employees);

}
