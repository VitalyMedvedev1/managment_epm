package ru.homework.andry.soap.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.andry.soap.builder.CreateEmployeeResponseBuilder;
import ru.homework.andry.soap.constant.Values;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceImplTest {

    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeMapper employeeMapper = mock(EmployeeMapper.class);
    private final CreateEmployeeResponseBuilder createEmployeeResponseBuilder = mock(CreateEmployeeResponseBuilder.class);
    private final Values values = new Values(10000, 35000, 50000, 150000, 150000, 175000);

    private final EmployeesServiceImpl employeesService = new EmployeesServiceImpl
            (
                    employeeRepository,
                    employeeMapper,
                    createEmployeeResponseBuilder,
                    new EmployeeDataValidationImpl()
            );
}