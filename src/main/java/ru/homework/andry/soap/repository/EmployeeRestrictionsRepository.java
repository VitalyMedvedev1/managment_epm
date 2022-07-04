package ru.homework.andry.soap.repository;

import io.dliga.micro.employee_web_service.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.andry.soap.entity.EmployeeRestrictionsEntity;

public interface EmployeeRestrictionsRepository extends JpaRepository<EmployeeRestrictionsEntity, Long> {

    EmployeeRestrictionsEntity findByPosition(Position position);
}
