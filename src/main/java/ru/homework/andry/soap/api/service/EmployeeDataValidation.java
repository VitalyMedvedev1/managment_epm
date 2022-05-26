package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.AbstractEmployee;

import java.util.List;

public interface EmployeeDataValidation {
    //todo все интерфейсы в отдельный пакет api
    // done

    List<AbstractEmployee> validate(List<AbstractEmployee> employees);

}
