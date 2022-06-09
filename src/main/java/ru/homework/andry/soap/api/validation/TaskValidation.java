package ru.homework.andry.soap.api.validation;

import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

public interface TaskValidation {

    boolean checkCountAssignTasks(int count, EmployeeEntity employeeEntity);

    List<UUID> validate(List<UUID> requestIds, List<UUID> foundIds);

}
