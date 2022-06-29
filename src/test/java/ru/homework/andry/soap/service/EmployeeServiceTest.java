package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.homework.andry.soap.api.service.EmployeeService;
import ru.homework.andry.soap.api.validation.EmployeeValidation;
import ru.homework.andry.soap.constant.PropertiesValue;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.mapper.EmployeeSwitcherMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.service.kafka.EmployeeSenderImpl;
import ru.homework.andry.soap.validation.EmployeeValidationImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.getEmployees;
import static ru.homework.andry.soap.testdata.ValueConstTestData.getValues;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeServiceTest {

    public static final String UPDATE_PROJECT = "OTP";
    public static final String UPDATE_DEVELOPER_LEVEL = "senior";
    public static final int UPDATE_SALARY = 155000;
    @Autowired
    private EmployeeRepository employeeRepository;

    private PropertiesValue propertiesValue = getValues();

    @MockBean
    private EmployeeValidation employeeValidation;

    private EmployeeService employeeService;

    @MockBean
    private EmployeeSenderImpl employeeSender;


    @BeforeEach
    void initService() {
        employeeService = new EmployeeServiceImpl(employeeRepository,
                                                  new EmployeeSwitcherMapper(Mappers.getMapper(EmployeeMapper.class)),
                                                  new EmployeeValidationImpl(), employeeSender);
    }

    @Test
    void create_TwoEmployees_OneDeveloperOneAnalytic() {
        List<Employee> employees = getEmployees(2,
                                                new int[]{130000, 150000},
                                                new Position[]{Position.DEVELOPER, Position.MANAGER});

        List<Employee> savedEmployees = employeeService.create(employees);

        assertThat(savedEmployees.size())
                .isEqualTo(employees.size());
    }
}