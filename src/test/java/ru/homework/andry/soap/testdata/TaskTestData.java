package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TaskTestData {

    public static Map<Position, Integer> getMaxCountTaskByPosition(Integer develop,
                                                                   Integer analytics,
                                                                   Integer manager) {
        return Map.of(Position.DEVELOPER, develop,
                      Position.ANALYTICS, analytics,
                      Position.MANAGER, manager);
    }

    public static List<TaskEntity> getTaskEntities(int count) {
        List<TaskEntity> tasks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tasks.add(getTask());
        }
        return tasks;
    }

    private static TaskEntity getTask() {
        return new TaskEntity(UUID.randomUUID(),
                              "",
                              null);
    }

    public static List<UUID> getUUIDs(int count){
        List<UUID> uuids = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            uuids.add(UUID.randomUUID());
        }
        return uuids;
    }
}
