package ru.homework.andry.soap.api.builder;

import ru.homework.andry.soap.element.AbstractEmployee;

import java.util.List;

public interface EmployeeResponseBuilder<T> {
    //todo все интерфейсы в отдельный пакет api
    // done

    void build(T response, List<AbstractEmployee> employees);
}