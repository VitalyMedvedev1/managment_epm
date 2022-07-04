package ru.homework.andry.soap.validation;

import io.dliga.micro.employee_web_service.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.getEmployeeEntity;
import static ru.homework.andry.soap.testdata.TaskTestData.*;

class TaskValidationTest {

    private final TaskValidationImpl validation = new TaskValidationImpl();

    @Test
    void checkCountAssignTasks_AddTasksToDeveloper_AlreadyHasMaximum() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.DEVELOPER);
        employee.getTasks().addAll(getTaskEntities(1, null));

        boolean result = validation.checkCountAssignTasks(1, employee);

        assertThat(result).isFalse();
    }

    @Test
    void checkCountAssignTasks_AddTasksToManager_AlreadyHasMaximum() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.MANAGER);
        employee.getTasks().addAll(getTaskEntities(3, null));

        boolean result = validation.checkCountAssignTasks(1, employee);

        assertThat(result).isFalse();
    }

    @Test
    void checkCountAssignTasks_AddTasksToAnalytics_AlreadyHasMaximum() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.ANALYTICS);
        employee.getTasks().addAll(getTaskEntities(2, null));

        boolean result = validation.checkCountAssignTasks(1, employee);

        assertThat(result).isFalse();
    }

    @Test
    void checkCountAssignTasks_AddTasksToDeveloper_MoreTaskCome() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.DEVELOPER);

        boolean result = validation.checkCountAssignTasks(2, employee);

        assertThat(result).isFalse();
    }

    @Test
    void checkCountAssignTasks_AddTasksToManager_MoreTaskCome() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.MANAGER);

        boolean result = validation.checkCountAssignTasks(4, employee);

        assertThat(result).isFalse();
    }

    @Test
    void checkCountAssignTasks_AddTasksToAnalytics_MoreTaskCome() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.ANALYTICS);

        boolean result = validation.checkCountAssignTasks(3, employee);

        assertThat(result).isFalse();
    }

    @Test
    void checkCountAssignTasks_AddTasksToDeveloper_AvailableCount() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.DEVELOPER);

        boolean result = validation.checkCountAssignTasks(1, employee);

        assertThat(result).isTrue();
    }

    @Test
    void checkCountAssignTasks_AddTasksToManager_AvailableCount() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.MANAGER);

        boolean result = validation.checkCountAssignTasks(3, employee);

        assertThat(result).isTrue();
    }

    @Test
    void checkCountAssignTasks_AddTasksToAnalytics_AvailableCount() {
        EmployeeEntity employee = getEmployeeEntity(1, Position.ANALYTICS);

        boolean result = validation.checkCountAssignTasks(2, employee);

        assertThat(result).isTrue();
    }

    @Test
    void validate_AllRequestTasksWasFound() {
        List<UUID> requestUUIDs = getUUIDs(3);
        List<UUID> foundUUIDs = new ArrayList<>(requestUUIDs);

        List<UUID> invalidTasks = validation.validate(requestUUIDs, foundUUIDs);

        assertThat(invalidTasks)
                .isEmpty();
    }

    @Test
    void validate_OneRequestTaskWasNotFound() {
        List<UUID> requestUUIDs = getUUIDs(3);
        List<UUID> foundUUIDs = new ArrayList<>(requestUUIDs);
        requestUUIDs.add(UUID.randomUUID());

        List<UUID> invalidTasks = validation.validate(requestUUIDs, foundUUIDs);

        assertThat(invalidTasks)
                .isNotEmpty();
    }

}