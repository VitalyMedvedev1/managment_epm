package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.Position;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.andry.soap.constant.Values;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.service.EmployeeDataValidation;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static io.dliga.micro.employee_web_service.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.constant.Values.REQUIRED_FIELD_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.constant.Values.SALARY_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;

@ExtendWith(MockitoExtension.class)
class EmployeeDataValidationImplTest {

    private final EmployeeDataValidation employeeDataValidation = new EmployeeDataValidationImpl();
    private final Values values = new Values(
            10000,
            35000,
            50000,
            150000,
            150000,
            175000);

    @Test
    void validate_EmployeesWithThreeDevelopers_SalaryAndFieldCorrect() {
        List<AbstractEmployee> developerElements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, DEVELOPER, DEVELOPER},
                        new int[]{100000, 110000, 150000},
                        "");

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElements);

        assertThat(employees.size())
                .isEqualTo(developerElements.size());
        assertThat(
                developerElements.stream()
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                        .count())
                .isEqualTo(employees.size());
    }

    @Test
    void validate_EmployeeWithThreeDevelopers_SalaryIncorrect_RequiredFieldCorrect() {
        List<AbstractEmployee> developerElements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, DEVELOPER, DEVELOPER},
                        new int[]{100, 110000, 150000},
                        "");

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElements);

        assertThat(employees.size())
                .isEqualTo(3);
        assertThat(employees.size())
                .isEqualTo(3);

        assertThat(
                developerElements.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                        .count())
                .isEqualTo(1);

        assertThat(
                developerElements.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                        .map(AbstractEmployee::getErrorIncorrectSalaryMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(SALARY_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void validate_EmployeeWithManager_SalaryIncorrectRequiredFieldCorrect() {
        List<AbstractEmployee> managerElement =
                getAbstractEmployees(
                        1,
                        new Position[]{MANAGER},
                        new int[]{50000},
                        "");
        List<AbstractEmployee> employees = employeeDataValidation.validate(managerElement);

        assertThat(employees.size())
                .isEqualTo(managerElement.size());

        assertThat(
                managerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                        .map(AbstractEmployee::getErrorIncorrectSalaryMessage)
                        .collect(Collectors.joining())
        )
                .isEqualTo(MessageFormat.format(SALARY_ERROR_TEXT_MESSAGE, MANAGER.value()));
    }

    @Test
    void validate_EmployeeWithManager_FiledProjectNameIsEmpty() {
        List<AbstractEmployee> managerElement = getAbstractEmployees(
                1,
                new Position[]{MANAGER},
                new int[]{160000},
                PROJECT_NAME_IS_EMPTY);

        List<AbstractEmployee> employees = employeeDataValidation.validate(managerElement);

        assertThat(employees.size()).isEqualTo(managerElement.size());

        assertThat(
                managerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(AbstractEmployee::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, MANAGER.value()));
    }

    @Test
    void validate_EmployeeWithDeveloper_FiledLanguageIsEmpty() {
        List<AbstractEmployee> developerElement = getAbstractEmployees(
                1,
                new Position[]{DEVELOPER},
                new int[]{100000},
                LANGUAGE_IS_EMPTY);

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElement);

        assertThat(employees.size())
                .isEqualTo(developerElement.size());

        assertThat(
                developerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(AbstractEmployee::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void validate_EmployeeWithDeveloper_FiledLevelIsEmpty() {
        List<AbstractEmployee> developerElement = getAbstractEmployees(
                1,
                new Position[]{DEVELOPER},
                new int[]{100000},
                LEVEL_IS_EMPTY);

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElement);

        assertThat(employees.size())
                .isEqualTo(developerElement.size());

        assertThat(
                developerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(AbstractEmployee::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void validate_EmployeeWithAnalytics_FiledTypeIsEmpty() {
        List<AbstractEmployee> analyticsElement = getAbstractEmployees(
                1,
                new Position[]{ANALYTICS},
                new int[]{25000},
                TYPE_IS_EMPTY);

        List<AbstractEmployee> employees = employeeDataValidation.validate(analyticsElement);

        assertThat(employees.size())
                .isEqualTo(analyticsElement.size());
        assertThat(
                analyticsElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(AbstractEmployee::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, ANALYTICS.value()));
    }
}
