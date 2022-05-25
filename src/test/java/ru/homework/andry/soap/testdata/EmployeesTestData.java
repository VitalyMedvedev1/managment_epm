package ru.homework.andry.soap.testdata;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.model.employee.AbstractEmployee;
import ru.homework.andry.soap.model.employee.AnalyticsElement;
import ru.homework.andry.soap.model.employee.DeveloperElement;
import ru.homework.andry.soap.model.employee.ManagerElement;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EmployeesTestData {

    private static final Long EMPLOYEE_ID = 11111L;
    public static final String PROJECT = "VTB";
    public static final String DEVELOPER_LANGUAGE = "java";
    public static final String DEVELOPER_LEVEL = "middle";
    public static final String DEVELOPER_NAME = "developer_test";
    public static final String MANAGER_NAME = "manager_test";
    public static final String ANALYTICS_NAME = "Analytics_test";
    public static final String PROJECT_NAME_IS_EMPTY = "PROJECT_NAME_IS_EMPTY";
    public static final String LANGUAGE_IS_EMPTY = "LANGUAGE_IS_EMPTY";
    public static final String LEVEL_IS_EMPTY = "LEVEL_IS_EMPTY";
    public static final String TYPE_IS_EMPTY = "TYPE_IS_EMPTY";
    public static final String ANALYTICS_TYPE = "Business";
    public static final String ERROR_ALL_ROWS = "ERROR_ROW";
    public static final String ERROR_TEXT_TEST_MESSAGE = "ERROR_TEXT_TEST_MESSAGE";
    public static final String ERROR_ROW_ANALYTICS = "ERROR_ROW_ANALYTICS";

    public static List<Employee> getEmployees(int count, int[] salaries, Position[] positions) {

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employees.add(getEmployee(salaries[i], positions[i]));
        }
        return employees;
    }

    private static Employee getEmployee(int salary, Position position) {
        Employee employee = new Employee();
        employee.setId(BigInteger.valueOf(EMPLOYEE_ID));
        employee.setFirstName("Manager_test");
        employee.setLastName("Manager_test");
        employee.setAge(44);
        employee.setSalary(salary);
        employee.setPosition(position);
        if (position.equals(Position.ANALYTICS)) {
            employee.setType(ANALYTICS_TYPE);
        } else if (position.equals(Position.DEVELOPER)) {
            employee.setLanguage(DEVELOPER_LANGUAGE);
            employee.setLevel(DEVELOPER_LEVEL);
        } else {
            employee.setProject(PROJECT);
        }
        return employee;
    }

    public static List<EmployeeEntity> getEmployeeEntities(int count,
                                                           Position[] positions) {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            employeeEntities.add(getEmployeeEntity(i, positions[i]));
        }

        return employeeEntities;
    }

    private static EmployeeEntity getEmployeeEntity(int i, Position position) {
        return new EmployeeEntity(
                EMPLOYEE_ID + i,
                DEVELOPER_NAME + i,
                DEVELOPER_NAME + i,
                20 + i,
                1 + i,
                "middle",
                "java",
                "business",
                "VTB",
                position);
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

        if (type_error.equals(ERROR_ROW_ANALYTICS) || type_error.equals(ERROR_ALL_ROWS)) {
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

        if (type_error.equals(ERROR_ALL_ROWS)) {
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

        if (type_error.equals(ERROR_ALL_ROWS)) {
            managerElement.setErrorRequiredMessage(ERROR_TEXT_TEST_MESSAGE);
        }

        return managerElement;
    }
}
