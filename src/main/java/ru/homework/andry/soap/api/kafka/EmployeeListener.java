package ru.homework.andry.soap.api.kafka;

import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeListener {

    void listenToCreate(EmployeeEntity entity);

    void listenToDelete(List<Long> ids);
}
