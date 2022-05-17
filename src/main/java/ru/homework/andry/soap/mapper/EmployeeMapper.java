package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import org.mapstruct.Mapper;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = Employee.class)
public interface EmployeeMapper {

    Employee employeeToEmployeeResponse(EmployeeEntity employee);

    List<EmployeeEntity> employeesToEmployeesEntity(List<Employee> employees);

    List<Employee> employeesEntityToEmployees(List<EmployeeEntity> employees);
}
