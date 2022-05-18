package ru.homework.andry.soap.mapper;

import io.dliga.micro.employee_web_service.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.model.AnalyticsElement;
import ru.homework.andry.soap.model.DeveloperElement;
import ru.homework.andry.soap.model.ManagerElement;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;

class EmployeeEntityMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeesFromSoapMsgToEmployeesElementByPosition_oneManagerAnalyticDeveloper() {
        List<Employee> employeesFromSoapMsg = getEmployeesFromWSWithRowsOneForEachPositions();
        List<AbstractEmployee> employeesElement = employeeMapper.employeesSoapMsgElements(employeesFromSoapMsg);

        assertThat(employeesFromSoapMsg.size()).isEqualTo(employeesElement.size());
        assertThat((int) employeesElement.stream().filter(s -> s instanceof ManagerElement).count()).isEqualTo(1);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof DeveloperElement).count()).isEqualTo(1);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof AnalyticsElement).count()).isEqualTo(1);
    }

    @Test
    void employeesFromSoapMsgToEmployeesElementByPosition_TreeDeveloper() {
        List<Employee> employeesFromSoapMsg = getEmployeesFromWSWithTreeRowsDeveloperPosition();
        List<AbstractEmployee> employeesElement = employeeMapper.employeesSoapMsgElements(employeesFromSoapMsg);

        assertThat(employeesFromSoapMsg.size()).isEqualTo( employeesElement.size());
        assertThat((int) employeesElement.stream().filter(s -> s instanceof ManagerElement).count()).isEqualTo(0);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof DeveloperElement).count()).isEqualTo(3);
        assertThat((int) employeesElement.stream().filter(s -> s instanceof AnalyticsElement).count()).isEqualTo(0);
    }

    @Test
    void elementsToEntities_TreeDeveloper_Entities() {
        List<AbstractEmployee> developerElements = getEmployeesTreeDeveloperElement(
                100, LANGUAGE_1, DEVELOPER_LEVEL,
                110000, LANGUAGE_2, DEVELOPER_LEVEL,
                150000, LANGUAGE_3, DEVELOPER_LEVEL
        );

        List<EmployeeEntity> employeeEntities = employeeMapper.elementsToEntities(developerElements);
        assertThat(employeeEntities.size()).isEqualTo(3);
    }

    @Test
    void elementsToEntities_ManagerElement_CorrectEntities() {
        List<AbstractEmployee> managerElement = getEmployeeManagerElement(
                111, PROJECT
        );
        ManagerElement expectedElement = (ManagerElement) managerElement.get(0);

        EmployeeEntity actualElement = employeeMapper.elementsToEntities(managerElement).get(0);

        assertThat(expectedElement.getId()).isEqualTo(actualElement.getId());
        assertThat(expectedElement.getFirstName()).isEqualTo(actualElement.getFirstname());
        assertThat(expectedElement.getLastName()).isEqualTo(actualElement.getLastname());
        assertThat(expectedElement.getPosition()).isEqualTo(actualElement.getPosition());
        assertThat(expectedElement.getSalary()).isEqualTo(actualElement.getSalary());
        assertThat(expectedElement.getAge()).isEqualTo(actualElement.getAge());
        assertThat(expectedElement.getProject()).isEqualTo(actualElement.getProject());
    }

    @Test
    void elementsToEntities_DeveloperElement_CorrectEntities() {
        List<AbstractEmployee> managerElement = getEmployeeManagerElement(
                111, PROJECT
        );
        ManagerElement expectedElement = (ManagerElement) managerElement.get(0);

        EmployeeEntity actualElement = employeeMapper.elementsToEntities(managerElement).get(0);

        assertThat(expectedElement.getId()).isEqualTo(actualElement.getId());
        assertThat(expectedElement.getFirstName()).isEqualTo(actualElement.getFirstname());
        assertThat(expectedElement.getLastName()).isEqualTo(actualElement.getLastname());
        assertThat(expectedElement.getPosition()).isEqualTo(actualElement.getPosition());
        assertThat(expectedElement.getSalary()).isEqualTo(actualElement.getSalary());
        assertThat(expectedElement.getAge()).isEqualTo(actualElement.getAge());
        assertThat(expectedElement.getProject()).isEqualTo(actualElement.getProject());
    }
}