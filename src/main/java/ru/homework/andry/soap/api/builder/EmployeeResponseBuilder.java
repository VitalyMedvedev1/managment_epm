package ru.homework.andry.soap.api.builder;

import ru.homework.andry.soap.element.EmployeeElement;

import java.util.List;

public interface EmployeeResponseBuilder<T> {

    void build(T response, List<EmployeeElement> employees);
}