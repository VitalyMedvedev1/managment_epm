package ru.homework.andry.soap.api.kafka;

import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeSender {

    void sendToCreate(List<EmployeeEntity> entities);

    void sendToDelete(List<Long> ids);
}
