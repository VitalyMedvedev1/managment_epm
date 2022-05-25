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
@SuppressWarnings("All")
public interface EmployeeMapper {//todo сделай маппер без бина и сделай обычным классом

    default List<EmployeeEntity> elementsToEntities(List<AbstractEmployee> employees) {
        return employees.stream()
                .map(this::elementToEntity)
                .collect(Collectors.toList());
    }

    private EmployeeEntity elementToEntity(AbstractEmployee employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return analyticsToEntity((AnalyticsElement) employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return developerToEntity((DeveloperElement) employee);
        }

        return managerToEntity((ManagerElement) employee);
    }

    EmployeeEntity developerToEntity(DeveloperElement employee);

    EmployeeEntity managerToEntity(ManagerElement employee);

    EmployeeEntity analyticsToEntity(AnalyticsElement employee);

    default List<AbstractEmployee> employeesToElements(List<Employee> employees) {
        return employees.stream()
                .map(this::employeeElement)
                .collect(Collectors.toList());
    }

    private AbstractEmployee employeeElement(Employee employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return employeeToAnalytics(employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return employeeToDeveloper(employee);
        }

        return employeeToManager(employee);
    }

    AnalyticsElement employeeToAnalytics(Employee employee);

    DeveloperElement employeeToDeveloper(Employee employee);

    ManagerElement employeeToManager(Employee employee);

    default List<AbstractEmployee> entityToElement(List<EmployeeEntity> employees) {
        return employees.stream()
                .map(this::entityToEmployee)
                .collect(Collectors.toList());
    }

    private AbstractEmployee entityToEmployee(EmployeeEntity entity) {
        if (entity.getPosition().equals(Position.ANALYTICS)) {
            return analyticsToElement(entity);
        }

        if (entity.getPosition().equals(Position.DEVELOPER)) {
            return developerToElement(entity);
        }

        return managerToElement(entity);
    }

    AnalyticsElement analyticsToElement(EmployeeEntity entity);

    DeveloperElement developerToElement(EmployeeEntity entity);

    ManagerElement managerToElement(EmployeeEntity entity);

    default List<Employee> elementsToEmployees(List<AbstractEmployee> employees) {
        return employees.stream()
                .map(this::elementToEmployee)
                .collect(Collectors.toList());
    }

    private Employee elementToEmployee(AbstractEmployee employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return analyticsToEmployee((AnalyticsElement) employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return developerToEmployee((DeveloperElement) employee);
        }

        return managerToEmployee((ManagerElement) employee);
    }

    Employee developerToEmployee(DeveloperElement employee);

    Employee managerToEmployee(ManagerElement employee);

    Employee analyticsToEmployee(AnalyticsElement employee);


    List<Employee> entitiesToEmployeesResponse(List<EmployeeEntity> employees);

    Employee entityToEmployeeResponse(EmployeeEntity employee);

    Employee elementToEmployeeResponse(AbstractEmployee element);
}
