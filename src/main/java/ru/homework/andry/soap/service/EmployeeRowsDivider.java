package ru.homework.andry.soap.service;

import ru.homework.andry.soap.model.AbstractEmployee;

import java.util.List;
import java.util.Map;

public interface EmployeeRowsDivider {

    Map<Boolean, List<AbstractEmployee>> divideOnCorrectAndIncorrect(List<AbstractEmployee> employees);
}
