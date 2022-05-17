package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import org.mapstruct.Mapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.model.AnalyticsElement;
import ru.homework.andry.soap.model.DeveloperElement;
import ru.homework.andry.soap.model.ManagerElement;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = Employee.class)
public interface EmployeeMapper {

    Employee employeeToEmployeeResponse(EmployeeEntity employee);

    List<EmployeeEntity> employeesToEmployeesEntity(List<Employee> employees);

    List<Employee> employeesEntityToEmployees(List<EmployeeEntity> employees);

    AnalyticsElement employeeToAnalytics(Employee employee);

    DeveloperElement employeeToDeveloper(Employee employee);

    ManagerElement employeeToManager(Employee employee);

    default List<AbstractEmployee> mapEmployeesToCurrentEmployeesByPosition(List<Employee> employees) {
        return employees.stream().map(this::mapWSEmployee).collect(Collectors.toList());
    }

    private AbstractEmployee mapWSEmployee(Employee employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return employeeToAnalytics(employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return employeeToDeveloper(employee);
        }

        return employeeToManager(employee);
    }
}
