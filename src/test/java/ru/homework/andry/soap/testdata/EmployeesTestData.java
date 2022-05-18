package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.model.AnalyticsElement;
import ru.homework.andry.soap.model.DeveloperElement;
import ru.homework.andry.soap.model.ManagerElement;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class EmployeesTestData {

    private static final Long EMPLOYEE_ID = 11111L;
    public static final String PROJECT = "VTB";
    public static final String LANGUAGE_1 = "java";
    public static final String LANGUAGE_2 = "python";
    public static final String LANGUAGE_3 = "fortran";
    public static final String DEVELOPER_LEVEL = "middle";

/*    public static List<EmployeeEntity> getEmployeesEntityToTestMapper() {
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
    }*/

    public static List<Employee> getEmployeesFromWSWithRowsOneForEachPositions() {

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
        analytics.setId(BigInteger.valueOf(EMPLOYEE_ID + 2));
        analytics.setFirstname("Analytics_test");
        analytics.setLastname("Analytics_test");
        analytics.setAge(33);
        analytics.setSalary(100000);
        analytics.setPosition(Position.ANALYTICS);
        analytics.setType("System");

        return Arrays.asList(manager, developer, analytics);
    }

    public static List<Employee> getEmployeesFromWSWithTreeRowsDeveloperPosition() {


        Employee developer1 = new Employee();
        developer1.setId(BigInteger.valueOf(EMPLOYEE_ID));
        developer1.setFirstname("developer1_test");
        developer1.setLastname("developer1_test");
        developer1.setAge(33);
        developer1.setSalary(100000);
        developer1.setPosition(Position.DEVELOPER);
        developer1.setLevel("middle_plus");
        developer1.setLanguage("java");

        Employee developer2 = new Employee();
        developer2.setId(BigInteger.valueOf(EMPLOYEE_ID + 1));
        developer2.setFirstname("developer2_test");
        developer2.setLastname("developer2_test");
        developer2.setAge(33);
        developer2.setSalary(100000);
        developer2.setPosition(Position.DEVELOPER);
        developer2.setLevel("middle_plus");
        developer2.setLanguage("java");

        Employee developer3 = new Employee();
        developer3.setId(BigInteger.valueOf(EMPLOYEE_ID + 2));
        developer3.setFirstname("developer3_test");
        developer3.setLastname("developer3_test");
        developer3.setAge(33);
        developer3.setSalary(100000);
        developer3.setPosition(Position.DEVELOPER);
        developer3.setLevel("middle_plus");
        developer3.setLanguage("java");

        return Arrays.asList(developer1, developer2, developer3);
    }

    public static List<AbstractEmployee> getEmployeesTreeDeveloperElement(int salary1, String language1, String level1,
                                                                          int salary2, String language2, String level2,
                                                                          int salary3, String language3, String level3) {
        return Arrays.asList(
                new DeveloperElement(
                        EMPLOYEE_ID,
                        "developer_test1",
                        "developer_test1",
                        11,
                        salary1,
                        Position.DEVELOPER,
                        language1,
                        level1
                ),
                new DeveloperElement(
                        EMPLOYEE_ID + 1,
                        "developer_test2",
                        "developer_test2",
                        21,
                        salary2,
                        Position.DEVELOPER,
                        language2,
                        level2
                ),
                new DeveloperElement(
                        EMPLOYEE_ID + 2,
                        "developer_test3",
                        "developer_test3",
                        71,
                        salary3,
                        Position.DEVELOPER,
                        language3,
                        level3
                )
        );
    }

    public static List<AbstractEmployee> getEmployeeManagerElement(int salary, String project) {
        return List.of(
                new ManagerElement(
                        EMPLOYEE_ID,
                        "manager_test",
                        "manager_test",
                        11,
                        salary,
                        Position.MANAGER,
                        project
                )
        );
    }

    public static List<AbstractEmployee> getEmployeeDeveloperElement(int salary, String language, String level) {
        return List.of(
                new DeveloperElement(
                        EMPLOYEE_ID,
                        "manager_test",
                        "manager_test",
                        11,
                        salary,
                        Position.DEVELOPER,
                        language,
                        level
                )
        );
    }    public static List<AbstractEmployee> getEmployeeAnalyticsElement(int salary, String type) {
        return List.of(
                new AnalyticsElement(
                        EMPLOYEE_ID,
                        "manager_test",
                        "manager_test",
                        11,
                        salary,
                        Position.ANALYTICS,
                        type
                )
        );
    }
}
