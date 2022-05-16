package ru.homework.andry.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.andry.soap.repository.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
