package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import org.mapstruct.Mapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.model.AnalyticsElement;
import ru.homework.andry.soap.model.DeveloperElement;
import ru.homework.andry.soap.model.ManagerElement;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = Employee.class)
@SuppressWarnings("All")
public interface EmployeeMapper {//todo сделай маппер без бина и сделай обычным классом
    // done переделал на сервис

    EmployeeEntity developerToEntity(DeveloperElement employee);

    EmployeeEntity managerToEntity(ManagerElement employee);

    EmployeeEntity analyticsToEntity(AnalyticsElement employee);

    AnalyticsElement employeeToAnalytics(Employee employee);

    DeveloperElement employeeToDeveloper(Employee employee);

    ManagerElement employeeToManager(Employee employee);

    AnalyticsElement analyticsToElement(EmployeeEntity entity);

    DeveloperElement developerToElement(EmployeeEntity entity);

    ManagerElement managerToElement(EmployeeEntity entity);

    Employee developerToEmployee(DeveloperElement employee);

    Employee managerToEmployee(ManagerElement employee);

    Employee analyticsToEmployee(AnalyticsElement employee);

    List<Employee> entitiesToEmployeesResponse(List<EmployeeEntity> employees);

    Employee entityToEmployeeResponse(EmployeeEntity employee);

    Employee elementToEmployeeResponse(AbstractEmployee element);
}
