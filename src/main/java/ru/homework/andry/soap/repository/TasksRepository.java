package ru.homework.andry.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.entity.TaskEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TasksRepository extends JpaRepository<TaskEntity, UUID> {

    Optional<List<TaskEntity>> findAllByEmployee(EmployeeEntity employee);

    Optional<List<TaskEntity>> findAllByIdInAndEmployee(List<UUID> ids, EmployeeEntity employee);

}
