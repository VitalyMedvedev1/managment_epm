package ru.homework.andry.soap.api.kafka;

import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeListener {

    void listenForUpsert(List<EmployeeEntity> entities);

    void listenForDelete(List<Long> ids);
}
