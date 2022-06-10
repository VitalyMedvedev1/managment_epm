package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import io.dliga.micro.employee_web_service.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.getEmployees;

class EmployeeSoapResponseBuilderImplTest {

    private final EmployeeSoapResponseBuilderImpl builder = new EmployeeSoapResponseBuilderImpl();

    @Test
    void buildGetEmployeesResponse_TreeDeveloper() {

        List<Employee> employees = getEmployees(3,
                                                new int[]{1, 2, 3,},
                                                new Position[]{Position.DEVELOPER,
                                                               Position.DEVELOPER,
                                                               Position.DEVELOPER});

        GetEmployeesResponse response = builder.buildGetEmployeesResponse(employees);

        assertThat(response.getEmployees().size())
                .isEqualTo(employees.size());
        assertThat(response.getStatus().getErrorCode())
                .isEqualTo(0);
    }

    @Test
    void buildCreateEmployeesResponse_AllEmployeesAreCorrect() {
        List<Employee> employees = getEmployees(3,
                                                new int[]{1, 2, 3,},
                                                new Position[]{Position.DEVELOPER,
                                                               Position.DEVELOPER,
                                                               Position.DEVELOPER});

        CreateEmployeesResponse response = builder.buildCreateEmployeesResponse(employees);

        assertThat(response.getEmployees().size())
                .isEqualTo(3);

        assertThat(response.getEmployees()
                           .stream()
                           .filter(emp -> emp.getStatus().getErrorCode() == 0)
                           .count())
                .isEqualTo(3);

        assertThat(response.getEmployees()
                           .stream()
                           .filter(emp -> emp.getStatus().getErrorCode() != 0)
                           .count())
                .isEqualTo(0);
    }

    @Test
    void buildCreateEmployeesResponse_OneEmployeeIsIncorrect() {
        List<Employee> employees = getEmployees(3,
                                                new int[]{1, 2, 3,},
                                                new Position[]{Position.DEVELOPER,
                                                               Position.DEVELOPER,
                                                               Position.DEVELOPER});
        employees.get(0).setErrorMessage("ERROR");

        CreateEmployeesResponse response = builder.buildCreateEmployeesResponse(employees);

        assertThat(response.getEmployees().size())
                .isEqualTo(3);

        assertThat(response.getEmployees()
                           .stream()
                           .filter(emp -> emp.getStatus().getErrorCode() == 0)
                           .count())
                .isEqualTo(2);

        assertThat(response.getEmployees()
                           .stream()
                           .filter(emp -> emp.getStatus().getErrorCode() != 0)
                           .count())
                .isEqualTo(1);
    }

    @Test
    void name() {

    }
}