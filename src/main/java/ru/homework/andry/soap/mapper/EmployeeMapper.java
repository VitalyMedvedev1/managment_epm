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
/*
    @Mappings({
            @Mapping(target = "firstname", source = "firstName"),
            @Mapping(target = "lastname", source = "lastName")
    })*/
    EmployeeEntity developerToEntity(DeveloperElement employee);

    EmployeeEntity managerToEntity(ManagerElement employee);

    EmployeeEntity analyticsToEntity(AnalyticsElement employee);

    default List<AbstractEmployee> employeesSoapMsgToElements(List<Employee> employees) {
        return employees.stream()
                .map(this::employeeSoapMsgToElement)
                .collect(Collectors.toList());
    }

    private AbstractEmployee employeeSoapMsgToElement(Employee employee) {
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

    List<Employee> entitiesToEmployeesResponse(List<EmployeeEntity> employees);

    Employee entityToEmployeeResponse(EmployeeEntity employee);

    Employee elementToEmployeeResponse(AbstractEmployee element);
}
