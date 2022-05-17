package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class EmployeesTestData {

    private static final Long EMPLOYEE_ID = 11111L;

    public static List<EmployeeEntity> employeesEntityToTestMapper() {
        return Arrays.asList(
                new EmployeeEntity(
                        EMPLOYEE_ID,
                        "TEST_FIRST_NAME_1",
                        "TEST_SECOND_NAME_1",
                        33,
                        Position.MANAGER
                ),
                new EmployeeEntity(
                        EMPLOYEE_ID,
                        "TEST_FIRST_NAME_2",
                        "TEST_SECOND_NAME_2",
                        33,
                        Position.DEVELOPER
                )
        );
    }

    public static List<Employee> employeesToTestMapper() {

        Employee manager = new Employee();
        manager.setId(BigInteger.valueOf(EMPLOYEE_ID));
        manager.setFirstname("Manager_test");
        manager.setLastname("Manager_test");
        manager.setAge(44);
        manager.setSalary(160000);
        manager.setProject("OTP");
        manager.setPosition(Position.MANAGER);

        Employee developer = new Employee();
        developer.setId(BigInteger.valueOf(EMPLOYEE_ID + 1));
        developer.setFirstname("Developer_test");
        developer.setLastname("Developer_test");
        developer.setAge(33);
        developer.setSalary(100000);
        developer.setPosition(Position.DEVELOPER);
        developer.setLevel("middle_plus");
        developer.setLanguage("java");

        Employee analytics = new Employee();
        analytics.setId(BigInteger.valueOf(EMPLOYEE_ID + 1));
        analytics.setFirstname("analytics_test");
        analytics.setLastname("analytics_test");
        analytics.setAge(33);
        analytics.setSalary(100000);
        analytics.setPosition(Position.ANALYTICS);
        analytics.setType("System");

        return Arrays.asList(manager, developer, analytics);
    }
}
