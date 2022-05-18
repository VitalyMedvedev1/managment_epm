package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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

    @Mappings({
            @Mapping(target = "firstname", source = "firstName"),
            @Mapping(target = "lastname", source = "lastName")
    })
    EmployeeEntity developerToEntity(DeveloperElement employee);

    @Mappings({
            @Mapping(target = "firstname", source = "firstName"),
            @Mapping(target = "lastname", source = "lastName")
    })
    EmployeeEntity managerToEntity(ManagerElement employee);

    @Mappings({
            @Mapping(target = "firstname", source = "firstName"),
            @Mapping(target = "lastname", source = "lastName")
    })
    EmployeeEntity analyticsToEntity(AnalyticsElement employee);

    List<Employee> entityToEmployeeSoapMsg(List<EmployeeEntity> employees);

    default List<AbstractEmployee> employeesSoapMsgElements(List<Employee> employees) {
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

    @Mappings({
            @Mapping(target = "firstName", source = "firstname"),
            @Mapping(target = "lastName", source = "lastname")
    })
    AnalyticsElement employeeToAnalytics(Employee employee);

    @Mappings({
            @Mapping(target = "firstName", source = "firstname"),
            @Mapping(target = "lastName", source = "lastname")
    })
    DeveloperElement employeeToDeveloper(Employee employee);

    @Mappings({
            @Mapping(target = "firstName", source = "firstname"),
            @Mapping(target = "lastName", source = "lastname")
    })
    ManagerElement employeeToManager(Employee employee);
}
