package ru.homework.andry.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.andry.soap.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Long> {
}
