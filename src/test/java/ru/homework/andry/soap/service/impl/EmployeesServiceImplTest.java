package ru.homework.andry.soap.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.andry.soap.builder.GetEmployeeResponseBuilder;
import ru.homework.andry.soap.constant.Values;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.testdata.EmployeesTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.dliga.micro.employee_web_service.Position.DEVELOPER;
import static io.dliga.micro.employee_web_service.Position.MANAGER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static ru.homework.andry.soap.constant.Values.SALARY_ERROR_TEXT_MESSAGE;

@ExtendWith(MockitoExtension.class)
class EmployeesServiceImplTest {

    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeMapper employeeMapper = mock(EmployeeMapper.class);
    private final GetEmployeeResponseBuilder getEmployeeResponseBuilder = mock(GetEmployeeResponseBuilder.class);
    private final Values values = new Values(10000, 35000, 50000, 150000, 150000, 175000);

    private final EmployeesServiceImpl employeesService = new EmployeesServiceImpl
            (
                    employeeRepository,
                    employeeMapper,
                    getEmployeeResponseBuilder,
                    new EmployeeRowsDividerImpl()
            );
}