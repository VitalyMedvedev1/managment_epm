package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import io.dliga.micro.employee_web_service.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.andry.soap.builder.impl.CreateEmployeeResponseBuilder;
import ru.homework.andry.soap.builder.impl.GetEmployeeResponseBuilder;
import ru.homework.andry.soap.constant.Values;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.dliga.micro.employee_web_service.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.homework.andry.soap.testdata.EmployeesTestData.getEmployeeEntities;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceImplTest {

    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    private final Values values = new Values(
            10000,
            35000,
            50000,
            150000,
            150000,
            175000);

    private final EmployeesServiceImpl employeesService =
            new EmployeesServiceImpl(
                    employeeRepository,
                    employeeMapper,
                    new EmployeeDataValidationImpl(),
                    Arrays.asList(
                            new GetEmployeeResponseBuilder(employeeMapper),
                            new CreateEmployeeResponseBuilder(employeeMapper)));

    @Test
    void findAll() {
        List<EmployeeEntity> employeeEntities = getEmployeeEntities(3, new Position[]{DEVELOPER, MANAGER, ANALYTICS});
        when(employeeRepository.findAll()).thenReturn(employeeEntities);

        List<Employee> employees = employeesService.findAll().getEmployees();

        assertThat(employees.size())
                .isEqualTo(employeeEntities.size());

        assertThat(
                employees.stream()
                        .map(Employee::getPosition).map(Enum::name)
                        .collect(Collectors.joining("")))
                .isEqualTo(DEVELOPER.name() + MANAGER.name() + ANALYTICS.name());
    }
}