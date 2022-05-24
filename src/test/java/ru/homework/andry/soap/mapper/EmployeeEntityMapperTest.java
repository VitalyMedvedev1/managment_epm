package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.Position;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.model.AnalyticsElement;
import ru.homework.andry.soap.model.DeveloperElement;
import ru.homework.andry.soap.model.ManagerElement;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.util.List;

import static io.dliga.micro.employee_web_service.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;

class EmployeeEntityMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeesSoapMsgToElements_EmployeesMsgWithEveryPosition_MapByPosition() {
        List<Employee> employeesFromSoapMsg =
                getEmployees(
                        3,
                        new int[]{1, 1, 1},
                        new Position[]{MANAGER, ANALYTICS, DEVELOPER});

        List<AbstractEmployee> employeesElement =
                employeeMapper.employeesToElements(employeesFromSoapMsg);

        assertThat(employeesFromSoapMsg.size())
                .isEqualTo(employeesElement.size());

        assertThat((int) employeesElement.stream().filter(s -> s instanceof ManagerElement).count())
                .isEqualTo(1);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof DeveloperElement).count())
                .isEqualTo(1);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof AnalyticsElement).count())
                .isEqualTo(1);
    }

    @Test
    void employeesSoapMsgToElements_EmployeesMsgWithThreeDeveloper_MapToDeveloper() {
        List<Employee> employeesFromSoapMsg =
                getEmployees(
                        3,
                        new int[]{1, 1, 1},
                        new Position[]{DEVELOPER, DEVELOPER, DEVELOPER});

        List<AbstractEmployee> employeesElement =
                employeeMapper.employeesToElements(employeesFromSoapMsg);

        assertThat(employeesFromSoapMsg.size())
                .isEqualTo(employeesElement.size());

        assertThat((int) employeesElement.stream().filter(s -> s instanceof AnalyticsElement).count())
                .isEqualTo(0);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof ManagerElement).count())
                .isEqualTo(0);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof DeveloperElement).count())
                .isEqualTo(3);
    }

    @Test
    void elementsToEntities_ElementsWithThreeDeveloper() {
        List<AbstractEmployee> developerElements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, DEVELOPER, DEVELOPER},
                        new int[]{1111, 2222, 3333},
                        "");

        List<EmployeeEntity> employeeEntities =
                employeeMapper.elementsToEntities(developerElements);

        assertThat(employeeEntities.size())
                .isEqualTo(3);
    }

    @Test
    void elementsToEntities_ElementsWithManager() {
        List<AbstractEmployee> managerElement =
                getAbstractEmployees(
                        1,
                        new Position[]{MANAGER},
                        new int[]{100},
                        "");
        ManagerElement expectedElement = (ManagerElement) managerElement.get(0);

        EmployeeEntity actualElement = employeeMapper.elementsToEntities(managerElement).get(0);

        assertThat(expectedElement.getId())
                .isEqualTo(actualElement.getId());
        assertThat(expectedElement.getFirstName())
                .isEqualTo(actualElement.getFirstName());
        assertThat(expectedElement.getLastName())
                .isEqualTo(actualElement.getLastName());
        assertThat(expectedElement.getPosition())
                .isEqualTo(actualElement.getPosition());
        assertThat(expectedElement.getSalary())
                .isEqualTo(actualElement.getSalary());
        assertThat(expectedElement.getAge())
                .isEqualTo(actualElement.getAge());
        assertThat(expectedElement.getProject())
                .isEqualTo(actualElement.getProject());
    }

    @Test
    void elementsToEntities_ElementsWithDeveloper() {
        List<AbstractEmployee> developers =
                getAbstractEmployees(
                        1,
                        new Position[]{DEVELOPER},
                        new int[]{100},
                        "");
        DeveloperElement expectedElement = (DeveloperElement) developers.get(0);

        EmployeeEntity actualElement = employeeMapper.elementsToEntities(developers).get(0);

        assertThat(expectedElement.getId())
                .isEqualTo(actualElement.getId());
        assertThat(expectedElement.getFirstName())
                .isEqualTo(actualElement.getFirstName());
        assertThat(expectedElement.getLastName())
                .isEqualTo(actualElement.getLastName());
        assertThat(expectedElement.getPosition())
                .isEqualTo(actualElement.getPosition());
        assertThat(expectedElement.getSalary())
                .isEqualTo(actualElement.getSalary());
        assertThat(expectedElement.getAge())
                .isEqualTo(actualElement.getAge());
        assertThat(expectedElement.getLanguage())
                .isEqualTo(actualElement.getLanguage());
        assertThat(expectedElement.getLevel())
                .isEqualTo(actualElement.getLevel());
    }
}