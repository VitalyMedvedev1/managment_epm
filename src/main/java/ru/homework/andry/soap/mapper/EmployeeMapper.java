package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import org.mapstruct.Mapper;
import ru.homework.andry.soap.element.AbstractEmployee;
import ru.homework.andry.soap.element.AnalyticsElement;
import ru.homework.andry.soap.element.DeveloperElement;
import ru.homework.andry.soap.element.ManagerElement;
import ru.homework.andry.soap.entity.EmployeeEntity;

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

    Employee elementToEmployeeResponse(AbstractEmployee element);
}
