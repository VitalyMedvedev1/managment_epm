package ru.homework.andry.soap.builder;

import ru.homework.andry.soap.model.AbstractEmployee;

import java.util.List;

public interface EmployeeResponseBuilder<T> { //todo все интерфейсы в отдельный пакет api
    void build(T response, List<AbstractEmployee> employees);
}