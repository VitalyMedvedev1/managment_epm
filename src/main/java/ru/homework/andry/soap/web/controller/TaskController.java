package ru.homework.andry.soap.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.homework.andry.soap.api.service.TaskService;
import ru.homework.andry.soap.element.task.TaskRequestCreateElement;
import ru.homework.andry.soap.element.task.TaskRequestUpdateElement;
import ru.homework.andry.soap.element.task.TaskResponseElement;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees/{employeeId}/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseElement> find(@PathVariable Long employeeId) {
        return taskService.findByEmployee(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable Long employeeId,
                       @RequestBody @Valid List<TaskRequestCreateElement> tasks) {
        taskService.create(employeeId, tasks);
    }

    @PutMapping
    public void update(@PathVariable Long employeeId,
                       @RequestBody @Valid List<TaskRequestUpdateElement> tasks) {
        taskService.update(employeeId, tasks);
    }

    @DeleteMapping("/{taskIds}")
    public void delete(@PathVariable Long employeeId,
                       @PathVariable List<UUID> taskIds) {
        taskService.delete(employeeId, taskIds);
    }
}