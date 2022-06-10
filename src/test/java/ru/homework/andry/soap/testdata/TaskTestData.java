package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.element.task.TaskRequestCreateElement;
import ru.homework.andry.soap.element.task.TaskRequestUpdateElement;
import ru.homework.andry.soap.element.task.TaskResponseElement;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskTestData {

    public static Map<Position, Integer> getMaxCountTaskByPosition(Integer develop,
                                                                   Integer analytics,
                                                                   Integer manager) {
        return Map.of(Position.DEVELOPER, develop,
                      Position.ANALYTICS, analytics,
                      Position.MANAGER, manager);
    }

    public static List<TaskEntity> getTaskEntities(int count, EmployeeEntity employee) {
        List<TaskEntity> tasks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tasks.add(getTask(employee));
        }
        return tasks;
    }

    private static TaskEntity getTask(EmployeeEntity employee) {
        return new TaskEntity(UUID.randomUUID(),
                              "",
                              employee);
    }

    public static List<TaskRequestCreateElement> getTasksRequestCreate(int count, EmployeeEntity employee) {
        List<TaskRequestCreateElement> tasks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tasks.add(getTaskRequestCreate(employee));
        }
        return tasks;
    }

    private static TaskRequestCreateElement getTaskRequestCreate(EmployeeEntity employee) {
        return new TaskRequestCreateElement("");
    }

    public static List<TaskRequestUpdateElement> getTasksRequestUpdate(int count, UUID id, String description) {
        List<TaskRequestUpdateElement> tasks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tasks.add(getTaskRequestUpdate(id, description));
        }
        return tasks;
    }

    private static TaskRequestUpdateElement getTaskRequestUpdate(UUID id, String description) {
        return new TaskRequestUpdateElement(id,description);
    }


    public static List<UUID> getUUIDs(int count){
        List<UUID> uuids = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            uuids.add(UUID.randomUUID());
        }
        return uuids;
    }

    public static List<UUID> getIds(List<TaskResponseElement> tasks) {
        return tasks.stream()
                    .map(TaskResponseElement::getId)
                    .collect(Collectors.toList());
    }
}
