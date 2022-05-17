package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.homework.andry.soap.testdata.EmployeesTestData.employeesToTestMapper;

import java.util.List;

class EmployeeEntityMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

/*    @Test
    void employeesToGetEmployeesResponse_twoEmpEnt_responseWith2elemEmployee() {
        List<EmployeeEntity> employeeEntities = employeesToTestMapper();

        GetEmployeesResponse getEmployeesResponse = employeeMapper.employeesToGetEmployeesResponse(employeeEntities);

        for (int i = 0; i < employeeEntities.size(); i++) {
            assertEquals(employeeEntities.get(i).getId(), Long.parseLong(String.valueOf(getEmployeesResponse.getEmployees().get(i).getId())));
            assertEquals(employeeEntities.get(i).getAge(), getEmployeesResponse.getEmployees().get(i).getAge());
            assertEquals(employeeEntities.get(i).getFirstname(), getEmployeesResponse.getEmployees().get(i).getFirstname());
            assertEquals(employeeEntities.get(i).getLastname(), getEmployeesResponse.getEmployees().get(i).getLastname());
        }
    }*/
}