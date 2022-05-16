package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.EmployeeResponse;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import org.mapstruct.Mapper;
import ru.homework.andry.soap.repository.entity.Employee;

import java.util.List;

@Mapper(componentModel = "spring", uses = Employee.class)
public interface EmployeeMapper {

    EmployeeResponse employeeToEmployeeResponse(Employee employee);

    default GetEmployeesResponse employeesToGetEmployeesResponse(List<Employee> employees) {
        if (employees == null) {
            return null;
        }
        GetEmployeesResponse getEmployeesResponse = new GetEmployeesResponse();
        employees.forEach(employee -> getEmployeesResponse.getEmployees().add(employeeToEmployeeResponse(employee)));
        return getEmployeesResponse;
    }
}
