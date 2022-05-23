package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.model.AnalyticsElement;
import ru.homework.andry.soap.model.DeveloperElement;
import ru.homework.andry.soap.model.ManagerElement;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeesTestData {

    private static final Long EMPLOYEE_ID = 11111L;
    public static final String PROJECT = "VTB";
    public static final String DEVELOPER_LANGUAGE = "java";
    public static final String LANGUAGE_2 = "python";
    public static final String LANGUAGE_3 = "fortran";
    public static final String DEVELOPER_LEVEL = "middle";
    public static final String DEVELOPER_NAME = "developer_test";
    public static final String MANAGER_NAME = "manager_test";
    public static final String ANALYTICS_NAME = "Analytics_test";
    public static final String PROJECT_NAME_IS_EMPTY = "PROJECT_NAME_IS_EMPTY";
    public static final String LANGUAGE_IS_EMPTY = "LANGUAGE_IS_EMPTY";
    public static final String LEVEL_IS_EMPTY = "LEVEL_IS_EMPTY";
    public static final String TYPE_IS_EMPTY = "TYPE_IS_EMPTY";
    public static final String ANALYTICS_TYPE = "Business";
    public static final String ERROR_ROW = "ERROR_ROW";
    public static final String ERROR_TEXT_TEST_MESSAGE = "ERROR_TEXT_TEST_MESSAGE";

    public static List<Employee> getEmployeesFromWSWithRowsOneForEachPositions() {

        Employee manager = new Employee();
        manager.setId(BigInteger.valueOf(EMPLOYEE_ID));
        manager.setFirstName("Manager_test");
        manager.setLastName("Manager_test");
        manager.setAge(44);
        manager.setSalary(160000);
        manager.setProject("OTP");
        manager.setPosition(Position.MANAGER);

        Employee developer = new Employee();
        developer.setId(BigInteger.valueOf(EMPLOYEE_ID + 1));
        developer.setFirstName("Developer_test");
        developer.setLastName("Developer_test");
        developer.setAge(33);
        developer.setSalary(100000);
        developer.setPosition(Position.DEVELOPER);
        developer.setLevel("middle_plus");
        developer.setLanguage("java");

        Employee analytics = new Employee();
        analytics.setId(BigInteger.valueOf(EMPLOYEE_ID + 2));
        analytics.setFirstName(ANALYTICS_NAME);
        analytics.setLastName(ANALYTICS_NAME);
        analytics.setAge(33);
        analytics.setSalary(100000);
        analytics.setPosition(Position.ANALYTICS);
        analytics.setType("System");

        return Arrays.asList(manager, developer, analytics);
    }

    public static List<Employee> getEmployeesFromWSWithTreeRowsDeveloperPosition() {

        Employee developer1 = new Employee();
        developer1.setId(BigInteger.valueOf(EMPLOYEE_ID));
        developer1.setFirstName("developer1_test");
        developer1.setLastName("developer1_test");
        developer1.setAge(33);
        developer1.setSalary(100000);
        developer1.setPosition(Position.DEVELOPER);
        developer1.setLevel("middle_plus");
        developer1.setLanguage("java");

        Employee developer2 = new Employee();
        developer2.setId(BigInteger.valueOf(EMPLOYEE_ID + 1));
        developer2.setFirstName("developer2_test");
        developer2.setLastName("developer2_test");
        developer2.setAge(33);
        developer2.setSalary(100000);
        developer2.setPosition(Position.DEVELOPER);
        developer2.setLevel("middle_plus");
        developer2.setLanguage("java");

        Employee developer3 = new Employee();
        developer3.setId(BigInteger.valueOf(EMPLOYEE_ID + 2));
        developer3.setFirstName("developer3_test");
        developer3.setLastName("developer3_test");
        developer3.setAge(33);
        developer3.setSalary(100000);
        developer3.setPosition(Position.DEVELOPER);
        developer3.setLevel("middle_plus");
        developer3.setLanguage("java");

        return Arrays.asList(developer1, developer2, developer3);
    }

    public static List<AbstractEmployee> getEmployeesTreeDeveloperElementOLD(int salary1,
                                                                             String language1,
                                                                             String level1,
                                                                             int salary2,
                                                                             String language2,
                                                                             String level2,
                                                                             int salary3,
                                                                             String language3,
                                                                             String level3) {
        return Arrays.asList(
                new DeveloperElement(
                        EMPLOYEE_ID,
                        "developer_test1",
                        "developer_test1",
                        11,
                        salary1,
                        Position.DEVELOPER,
                        language1,
                        level1),
                new DeveloperElement(
                        EMPLOYEE_ID + 1,
                        "developer_test2",
                        "developer_test2",
                        21,
                        salary2,
                        Position.DEVELOPER,
                        language2,
                        level2),
                new DeveloperElement(
                        EMPLOYEE_ID + 2,
                        "developer_test3",
                        "developer_test3",
                        71,
                        salary3,
                        Position.DEVELOPER,
                        language3,
                        level3));
    }

    public static List<AbstractEmployee> getAbstractEmployees(int count,
                                                              Position[] positions,
                                                              int[] salaries,
                                                              String type_error) {
        List<AbstractEmployee> abstractEmployees = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (positions[i].equals(Position.ANALYTICS)) {
                abstractEmployees.add(getAnalytics(i, salaries[i], type_error));
            } else if (positions[i].equals(Position.DEVELOPER)) {
                abstractEmployees.add(getDeveloper(i, salaries[i], type_error));
            } else {
                abstractEmployees.add(getManager(i, salaries[i], type_error));
            }
        }

        return abstractEmployees;
    }


    public static AbstractEmployee getAnalytics(int i, int salary, String type_error) {
        String type = ANALYTICS_TYPE;
        if (type_error.equals(TYPE_IS_EMPTY)) {
            type = "";
        }
        AnalyticsElement analyticsElement = new AnalyticsElement(
                EMPLOYEE_ID,
                ANALYTICS_NAME + i,
                ANALYTICS_NAME + i,
                11,
                salary,
                Position.ANALYTICS,
                type);

        if (type_error.equals(ERROR_ROW)) {
            analyticsElement.setErrorRequiredMessage(ERROR_TEXT_TEST_MESSAGE);
        }

        return analyticsElement;
    }

    public static AbstractEmployee getDeveloper(int i, int salary, String type_error) {
        String language = DEVELOPER_LANGUAGE;
        if (type_error.equals(LANGUAGE_IS_EMPTY)) {
            language = "";
        }
        String level = DEVELOPER_LEVEL;
        if (type_error.equals(LEVEL_IS_EMPTY)) {
            level = "";
        }
        DeveloperElement developerElement = new DeveloperElement(
                EMPLOYEE_ID,
                DEVELOPER_NAME + i,
                DEVELOPER_NAME + i,
                11,
                salary,
                Position.DEVELOPER,
                language,
                level);

        if (type_error.equals(ERROR_ROW)) {
            developerElement.setErrorRequiredMessage(ERROR_TEXT_TEST_MESSAGE);
        }

        return developerElement;
    }

    public static AbstractEmployee getManager(int i, int salary, String type_error) {
        String projectName = PROJECT;
        if (type_error.equals(PROJECT_NAME_IS_EMPTY)) {
            projectName = "";
        }

        ManagerElement managerElement = new ManagerElement(
                EMPLOYEE_ID,
                MANAGER_NAME + i,
                MANAGER_NAME + i,
                11,
                salary,
                Position.MANAGER,
                projectName);

        if (type_error.equals(ERROR_ROW)) {
            managerElement.setErrorRequiredMessage(ERROR_TEXT_TEST_MESSAGE);
        }

        return managerElement;
    }
}
