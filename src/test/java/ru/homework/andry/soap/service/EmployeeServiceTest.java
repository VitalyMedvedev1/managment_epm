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
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.mapper.EmployeeSwitcherMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.validation.EmployeeValidationImpl;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;
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

    @BeforeEach
    void initService() {
        employeeService = new EmployeeServiceImpl(employeeRepository,
                                                  new EmployeeSwitcherMapper(
                                                          Mappers.getMapper(EmployeeMapper.class)),
                                                  new EmployeeValidationImpl());
    }

    @Test
    void create_TwoEmployees_OneDeveloperOneAnalytic() {
        List<Employee> employees = getEmployees(2,
                                                new int[]{130000, 150000},
                                                new Position[]{Position.DEVELOPER, Position.MANAGER});

        List<Employee> savedEmployees = employeeService.create(employees);


        List<EmployeeEntity> foundEmployees = employeeRepository.findAllById(getEntityIds(savedEmployees));

        assertThat(foundEmployees.size())
                .isEqualTo(savedEmployees.size());

        assertThat(foundEmployees
                           .stream()
                           .map(EmployeeEntity::getPosition)
                           .filter(p -> p.equals(Position.DEVELOPER))
                           .count())
                .isEqualTo(1);

        assertThat(foundEmployees
                           .stream()
                           .map(EmployeeEntity::getPosition)
                           .filter(p -> p.equals(Position.MANAGER))
                           .count())
                .isEqualTo(1);
    }

    @Test
    void update_OneRequestManager_UpdateProject() {
        List<Employee> employees = getEmployees(1,
                                                new int[]{150000},
                                                new Position[]{Position.MANAGER});
        List<Employee> savedEmployees = employeeService.create(employees);
        List<Employee> request = savedEmployees.stream()
                                               .peek(employee -> {
                                                   employee.setProject(UPDATE_PROJECT);
                                               }).collect(Collectors.toList());

        employeeService.update(request);

        assertThat(savedEmployees.get(0).getProject())
                .isNotEqualTo(PROJECT);

        assertThat(savedEmployees.get(0).getProject())
                .isEqualTo(UPDATE_PROJECT);
    }

    @Test
    void update_OneRequestDeveloper_UpdateLevel() {
        List<Employee> employees = getEmployees(1,
                                                new int[]{140000},
                                                new Position[]{Position.DEVELOPER});
        List<Employee> savedEmployees = employeeService.create(employees);
        List<Employee> request = savedEmployees.stream()
                                               .peek(employee -> {
                                                   employee.setLevel(UPDATE_DEVELOPER_LEVEL);
                                               }).collect(Collectors.toList());

        employeeService.update(request);

        assertThat(savedEmployees.get(0).getLevel())
                .isNotEqualTo(DEVELOPER_LEVEL);

        assertThat(savedEmployees.get(0).getLevel())
                .isEqualTo(UPDATE_DEVELOPER_LEVEL);
    }

    @Test
    void delete() {
        List<Employee> employees = getEmployees(2,
                                                new int[]{130000, 150000},
                                                new Position[]{Position.DEVELOPER, Position.MANAGER});

        List<Employee> savedEmployees = employeeService.create(employees);
        List<Long> requestIds = getEntityIds(savedEmployees);

        employeeService.delete(requestIds);
        List<EmployeeEntity> foundEmployees = employeeRepository.findAllById(requestIds);

        assertThat(foundEmployees).isEmpty();

    }
}