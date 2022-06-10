package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.homework.andry.soap.api.service.TaskService;
import ru.homework.andry.soap.element.task.TaskRequestCreateElement;
import ru.homework.andry.soap.element.task.TaskResponseElement;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.entity.TaskEntity;
import ru.homework.andry.soap.mapper.TaskMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.TasksRepository;
import ru.homework.andry.soap.validation.TaskValidationImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.getEmployeeEntity;
import static ru.homework.andry.soap.testdata.TaskTestData.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskServiceTest {

    public static final String UPDATE_DESC = "UPDATE_DESC";
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private TaskService taskService;
    private TaskValidationImpl validation = new TaskValidationImpl();

    @BeforeEach
    void initService() {
        taskService = new TaskServiceImpl(validation,
                                          tasksRepository,
                                          employeeRepository,
                                          Mappers.getMapper(TaskMapper.class));

        validation.getMaxCountTask().putAll(getMaxCountTaskByPosition(1,
                                                                      2,
                                                                      3));
    }

    @Test
    void findByEmployee() {
        EmployeeEntity employee = employeeRepository.save(getEmployeeEntity(1, Position.MANAGER));
        List<TaskEntity> savedTasks = tasksRepository.saveAll(getTaskEntities(1, employee));
        employee.getTasks().addAll(savedTasks);

        List<TaskResponseElement> tasks = taskService.findByEmployee(employee.getId());

        assertThat(tasks).isNotEmpty();
        assertThat(tasks.size()).isEqualTo(1);
    }

    @Test
    void create() {
        EmployeeEntity employee = employeeRepository.save(getEmployeeEntity(1, Position.MANAGER));

        List<TaskRequestCreateElement> request = getTasksRequestCreate(2, null);

        taskService.create(employee.getId(), request);

        List<TaskResponseElement> tasks = taskService.findByEmployee(employee.getId());

        assertThat(tasks.size())
                .isEqualTo(2);

    }

    @Test
    void update() {
        EmployeeEntity employee = employeeRepository.save(getEmployeeEntity(1, Position.MANAGER));
        List<TaskRequestCreateElement> request = getTasksRequestCreate(1, null);
        taskService.create(employee.getId(), request);
        List<TaskResponseElement> tasks = taskService.findByEmployee(employee.getId());

        taskService.update(employee.getId(),
                           getTasksRequestUpdate(1, tasks.get(0).getId(), UPDATE_DESC));

        TaskResponseElement updateTask = taskService.findByEmployee(employee.getId()).get(0);

        assertThat(updateTask.getDescription())
                .isEqualTo(UPDATE_DESC);
        assertThat(updateTask.getDescription())
                .isNotEqualTo("");
    }

    @Test
    void delete() {
        EmployeeEntity employee = employeeRepository.save(getEmployeeEntity(1, Position.MANAGER));
        List<TaskRequestCreateElement> request = getTasksRequestCreate(2, null);
        taskService.create(employee.getId(), request);
        List<TaskResponseElement> tasks = taskService.findByEmployee(employee.getId());

        taskService.delete(employee.getId(), getIds(tasks));

        taskService.findByEmployee(employee.getId());

        assertThat(taskService.findByEmployee(employee.getId()))
                .isEmpty();

    }
}