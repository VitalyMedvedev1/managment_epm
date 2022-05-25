package ru.homework.andry.soap.repository;

import io.dliga.micro.employee_web_service.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByPosition(Position position);
}
