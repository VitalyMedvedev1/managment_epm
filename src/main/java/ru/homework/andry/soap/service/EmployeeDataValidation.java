package ru.homework.andry.soap.service;

import ru.homework.andry.soap.model.AbstractEmployee;

import java.util.List;

public interface EmployeeDataValidation { //todo все интерфейсы в отдельный пакет api

    List<AbstractEmployee> validate(List<AbstractEmployee> employees);

}
