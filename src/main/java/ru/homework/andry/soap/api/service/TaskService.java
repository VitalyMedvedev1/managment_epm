package ru.homework.andry.soap.api.service;

import ru.homework.andry.soap.element.task.TaskRequestCreateElement;
import ru.homework.andry.soap.element.task.TaskRequestUpdateElement;
import ru.homework.andry.soap.element.task.TaskResponseElement;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskResponseElement> findByEmployee(Long employeeId);

    void create(Long employeeId, List<TaskRequestCreateElement> tasks);

    void update(Long employeeId, List<TaskRequestUpdateElement> tasks);

    void delete(Long employeeId, List<UUID> requestIds);
}