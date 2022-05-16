package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.repository.entity.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeesTestData {

    private static final Long EMPLOYEE_ID = 11111L;

    public static List<Employee> employeesToTestMapper(){
        return Arrays.asList(
                new Employee(
                        EMPLOYEE_ID,
                        "TEST_FIRST_NAME_1",
                        "TEST_SECOND_NAME_1",
                        33,
                        Position.MANAGER
                ),
                new Employee(
                        EMPLOYEE_ID,
                        "TEST_FIRST_NAME_2",
                        "TEST_SECOND_NAME_2",
                        33,
                        Position.DEVELOPER
                )
        );
    }
}
