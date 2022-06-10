package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.homework.andry.soap.element.employee.AnalyticsElement;
import ru.homework.andry.soap.element.employee.DeveloperElement;
import ru.homework.andry.soap.element.employee.ManagerElement;
import ru.homework.andry.soap.entity.EmployeeEntity;

@Mapper(componentModel = "spring",
        uses = Employee.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeMapper {

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

}
