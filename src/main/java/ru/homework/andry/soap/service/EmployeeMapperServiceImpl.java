package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.element.AbstractEmployee;
import ru.homework.andry.soap.element.AnalyticsElement;
import ru.homework.andry.soap.element.DeveloperElement;
import ru.homework.andry.soap.element.ManagerElement;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeMapperServiceImpl {

    private final EmployeeMapper employeeMapper;

    public List<EmployeeEntity> elementsToEntities(List<AbstractEmployee> employees) {
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

    private EmployeeEntity developerToEntity(DeveloperElement employee) {
        return employeeMapper.developerToEntity(employee);
    }

    private EmployeeEntity managerToEntity(ManagerElement employee) {
        return employeeMapper.managerToEntity(employee);
    }

    private EmployeeEntity analyticsToEntity(AnalyticsElement employee) {
        return employeeMapper.analyticsToEntity(employee);
    }

    public List<AbstractEmployee> employeesToElements(List<Employee> employees) {
        return employees.stream()
                .map(this::employeeElement)
                .collect(Collectors.toList());
    }

    private AbstractEmployee employeeElement(Employee employee) { //todo в названии метода нет действия
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

    public List<AbstractEmployee> entityToElement(List<EmployeeEntity> employees) {
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

    private AnalyticsElement analyticsToElement(EmployeeEntity entity) {
        return employeeMapper.analyticsToElement(entity);
    }

    private DeveloperElement developerToElement(EmployeeEntity entity) {
        return employeeMapper.developerToElement(entity);
    }

    private ManagerElement managerToElement(EmployeeEntity entity) {
        return employeeMapper.managerToElement(entity);
    }

    public List<Employee> elementsToEmployees(List<AbstractEmployee> employees) {
        return employees.stream()
                .map(this::elementToEmployee)
                .collect(Collectors.toList());
    }

    private Employee elementToEmployee(AbstractEmployee employee) { //todo не используется
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

    public Employee elementToEmployeeResponse(AbstractEmployee element) {
        return employeeMapper.elementToEmployeeResponse(element);
    }
}
