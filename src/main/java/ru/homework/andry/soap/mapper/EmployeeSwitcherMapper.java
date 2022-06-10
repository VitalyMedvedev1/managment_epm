package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.element.employee.AnalyticsElement;
import ru.homework.andry.soap.element.employee.DeveloperElement;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.element.employee.ManagerElement;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeSwitcherMapper {

    private final EmployeeMapper employeeMapper;

    public List<EmployeeEntity> elementsToEntities(List<EmployeeElement> employees) {
        return employees.stream()
                        .map(this::elementToEntity)
                        .collect(Collectors.toList());
    }

    private EmployeeEntity elementToEntity(EmployeeElement employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return analyticsToEntity((AnalyticsElement) employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return developerToEntity((DeveloperElement) employee);
        }

        return managerToEntity((ManagerElement) employee);
    }

    private EmployeeEntity developerToEntity(DeveloperElement employee) {
        return employeeMapper.developerToEntity(employee);
    }

    private EmployeeEntity managerToEntity(ManagerElement employee) {
        return employeeMapper.managerToEntity(employee);
    }

    private EmployeeEntity analyticsToEntity(AnalyticsElement employee) {
        return employeeMapper.analyticsToEntity(employee);
    }

    public List<EmployeeElement> employeesToElements(List<Employee> employees) {
        return employees.stream()
                        .map(this::employeeToElement)
                        .collect(Collectors.toList());
    }

    private EmployeeElement employeeToElement(Employee employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return employeeToAnalytics(employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return employeeToDeveloper(employee);
        }

        return employeeToManager(employee);
    }

    private AnalyticsElement employeeToAnalytics(Employee employee) {
        return employeeMapper.employeeToAnalytics(employee);
    }

    private DeveloperElement employeeToDeveloper(Employee employee) {
        return employeeMapper.employeeToDeveloper(employee);
    }

    private ManagerElement employeeToManager(Employee employee) {
        return employeeMapper.employeeToManager(employee);
    }

    public List<EmployeeElement> entityToElement(List<EmployeeEntity> employees) {
        return employees.stream()
                        .map(this::entityToEmployee)
                        .collect(Collectors.toList());
    }

    private EmployeeElement entityToEmployee(EmployeeEntity entity) {
        if (entity.getPosition().equals(Position.ANALYTICS)) {
            return analyticsToElement(entity);
        }

        if (entity.getPosition().equals(Position.DEVELOPER)) {
            return developerToElement(entity);
        }

        return managerToElement(entity);
    }

    private AnalyticsElement analyticsToElement(EmployeeEntity entity) {
        return employeeMapper.analyticsToElement(entity);
    }

    private DeveloperElement developerToElement(EmployeeEntity entity) {
        return employeeMapper.developerToElement(entity);
    }

    private ManagerElement managerToElement(EmployeeEntity entity) {
        return employeeMapper.managerToElement(entity);
    }

    public List<Employee> elementsToEmployees(List<EmployeeElement> employees) {
        return employees.stream()
                        .map(this::elementToEmployee)
                        .collect(Collectors.toList());
    }

    private Employee elementToEmployee(EmployeeElement employee) {
        if (employee.getPosition().equals(Position.ANALYTICS)) {
            return analyticsToEmployee((AnalyticsElement) employee);
        }

        if (employee.getPosition().equals(Position.DEVELOPER)) {
            return developerToEmployee((DeveloperElement) employee);
        }

        return managerToEmployee((ManagerElement) employee);
    }

    private Employee developerToEmployee(DeveloperElement employee) {
        return employeeMapper.developerToEmployee(employee);
    }

    private Employee managerToEmployee(ManagerElement employee) {
        return employeeMapper.managerToEmployee(employee);
    }

    private Employee analyticsToEmployee(AnalyticsElement employee) {
        return employeeMapper.analyticsToEmployee(employee);
    }

}
