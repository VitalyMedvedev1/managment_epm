package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.andry.soap.repository.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.homework.andry.soap.testdata.EmployeesTestData.employeesToTestMapper;

import java.util.List;

class EmployeeMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeesToGetEmployeesResponse_twoEmpEnt_responseWith2elemEmployee() {
        List<Employee> employees = employeesToTestMapper();

        GetEmployeesResponse getEmployeesResponse = employeeMapper.employeesToGetEmployeesResponse(employees);

        for (int i = 0; i < employees.size(); i++) {
            assertEquals(employees.get(i).getId(), Long.parseLong(String.valueOf(getEmployeesResponse.getEmployees().get(i).getId())));
            assertEquals(employees.get(i).getAge(), getEmployeesResponse.getEmployees().get(i).getAge());
            assertEquals(employees.get(i).getFirstname(), getEmployeesResponse.getEmployees().get(i).getFirstname());
            assertEquals(employees.get(i).getLastname(), getEmployeesResponse.getEmployees().get(i).getLastname());
        }
    }
}