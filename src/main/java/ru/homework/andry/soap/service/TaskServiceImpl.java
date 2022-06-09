package ru.homework.andry.soap.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.api.service.TaskService;
import ru.homework.andry.soap.api.validation.TaskValidation;
import ru.homework.andry.soap.element.task.TaskRequestCreateElement;
import ru.homework.andry.soap.element.task.TaskRequestUpdateElement;
import ru.homework.andry.soap.element.task.TaskResponseElement;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.entity.TaskEntity;
import ru.homework.andry.soap.exeption.BusinessLogicException;
import ru.homework.andry.soap.mapper.TaskMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.TasksRepository;

import javax.persistence.EntityNotFoundException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskValidation taskValidation;
    private final TasksRepository tasksRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponseElement> findByEmployee(Long employeeId) {
        log.info("Start finding task by employee id: {}", employeeId);
        EmployeeEntity employee = findEmployee(employeeId);

        return taskMapper.entitiesToResponseElements(tasksRepository.findAllByEmployee(employee)
                                                                    .orElse(Collections.emptyList()));
    }

    private EmployeeEntity findEmployee(Long employeeId) {
        log.debug("Finding employee by id: {}", employeeId);
        return employeeRepository.findById(employeeId)
                                 .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                                                 "Employee with id: {0} not found",
                                                 employeeId)));
    }

    @Override
    public void create(Long employeeId, List<TaskRequestCreateElement> requestTasks) {
        log.info("Start create tasks to employee: {}", employeeId);
        EmployeeEntity employee = findEmployee(employeeId);

        boolean allowedCountTasks = taskValidation.checkCountAssignTasks(requestTasks.size(), employee);

        if (!allowedCountTasks) {
            throw new BusinessLogicException("You can't assign a task amount to an employee: " + employeeId);
        }

        List<TaskEntity> taskEntities =
                tasksRepository.saveAll(taskMapper.requestCreateToEntity(requestTasks));

        employee.getTasks().addAll(taskEntities);

        log.info("Successful save tasks: {} to employee: {}", mapIds(taskEntities), employeeId);
    }

    private List<UUID> mapIds(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                           .map(TaskEntity::getId)
                           .collect(Collectors.toList());
    }

    @Override
    public void update(Long employeeId, List<TaskRequestUpdateElement> requestTasks) {
        log.info("Start update tasks to employee: {}", employeeId);
        List<UUID> requestIds = getRequestIds(requestTasks);

        EmployeeEntity employee = findEmployee(employeeId);

        List<UUID> invalidIds = getInvalidIds(requestIds, employee);

        if (!invalidIds.isEmpty()) {
            throw new BusinessLogicException
                    (MessageFormat.format("Notes with number: {0} are not valid", invalidIds));
        }

        List<TaskEntity> taskEntities = tasksRepository.saveAll(taskMapper.requestUpdateToEntity(requestTasks));

        employee.getTasks().addAll(taskEntities);

        log.info("Successful update tasks with ids {}: ", requestIds);
    }
    private List<UUID> getInvalidIds(List<UUID> requestIds, EmployeeEntity employee) {
        log.debug("Start getting invalids ids from request");
        List<TaskEntity> foundTasks = findAllByIdInAndEmployee(requestIds, employee);
        List<UUID> foundTaskIds = mapIds(foundTasks);

        return taskValidation.validate(requestIds, foundTaskIds);
    }


    private List<TaskEntity> findAllByIdInAndEmployee(List<UUID> requestIds, EmployeeEntity employee) {
        log.debug("Start finding employee tasks: {}, with id: {}", requestIds, employee.getId());
        return tasksRepository.findAllByIdInAndEmployee(requestIds, employee)
                             .orElse(Collections.emptyList());
    }

    private List<UUID> getRequestIds(List<TaskRequestUpdateElement> requestTasks) {
        return requestTasks.stream()
                           .map(TaskRequestUpdateElement::getId)
                           .collect(Collectors.toList());
    }


    @Override
    public void delete(Long employeeId, List<UUID> requestIds) {
        log.info("Start update tasks to employee: {}", employeeId);

        EmployeeEntity employee = findEmployee(employeeId);

        List<UUID> invalidIds = getInvalidIds(requestIds, employee);

        if (!invalidIds.isEmpty()) {
            throw new BusinessLogicException
                    (MessageFormat.format("Notes with number: {0} are not valid", invalidIds));
        }

        tasksRepository.deleteAllById(requestIds);
        log.info("Successful delete tasks by ids: {}", requestIds);
    }
}
