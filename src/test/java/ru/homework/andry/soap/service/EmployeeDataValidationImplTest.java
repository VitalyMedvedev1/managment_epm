package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.Position;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.andry.soap.constant.PropertiesValue;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.api.service.EmployeeDataValidation;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static io.dliga.micro.employee_web_service.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.constant.PropertiesValue.REQUIRED_FIELD_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.constant.PropertiesValue.SALARY_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;
import static ru.homework.andry.soap.testdata.ValueConstTestData.getValues;

@ExtendWith(MockitoExtension.class)
class EmployeeDataValidationImplTest {

    private final EmployeeDataValidation employeeDataValidation = new EmployeeDataValidationImpl();
    private final PropertiesValue propertiesValue = getValues();

    @Test
    void validate_EmployeesWithThreeDevelopers_SalaryAndFieldCorrect() {
        List<EmployeeElement> developerElements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, DEVELOPER, DEVELOPER},
                        new int[]{100000, 110000, 150000},
                        "");

        employeeDataValidation.validate(developerElements);

        assertThat(
                developerElements.stream()
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                        .count())
                .isEqualTo(3);
    }

    @Test
    void validate_EmployeeWithThreeDevelopers_SalaryIncorrect_RequiredFieldCorrect() {
        List<EmployeeElement> developerElements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, DEVELOPER, DEVELOPER},
                        new int[]{100, 110000, 150000},
                        "");

        employeeDataValidation.validate(developerElements);

        assertThat(developerElements.size())
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
                        .map(EmployeeElement::getErrorIncorrectSalaryMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(SALARY_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void validate_EmployeeWithManager_SalaryIncorrectRequiredFieldCorrect() {
        List<EmployeeElement> managerElement =
                getAbstractEmployees(
                        1,
                        new Position[]{MANAGER},
                        new int[]{50000},
                        "");
       employeeDataValidation.validate(managerElement);

        assertThat(
                managerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                        .map(EmployeeElement::getErrorIncorrectSalaryMessage)
                        .collect(Collectors.joining())
        )
                .isEqualTo(MessageFormat.format(SALARY_ERROR_TEXT_MESSAGE, MANAGER.value()));
    }

    @Test
    void validate_EmployeeWithManager_FiledProjectNameIsEmpty() {
        List<EmployeeElement> managerElement = getAbstractEmployees(
                1,
                new Position[]{MANAGER},
                new int[]{160000},
                PROJECT_NAME_IS_EMPTY);

        employeeDataValidation.validate(managerElement);

        assertThat(managerElement.size())
                .isEqualTo(1);

        assertThat(
                managerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(EmployeeElement::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, MANAGER.value()));
    }

    @Test
    void validate_EmployeeWithDeveloper_FiledLanguageIsEmpty() {
        List<EmployeeElement> developerElement = getAbstractEmployees(
                1,
                new Position[]{DEVELOPER},
                new int[]{100000},
                LANGUAGE_IS_EMPTY);

        employeeDataValidation.validate(developerElement);

        assertThat(developerElement.size())
                .isEqualTo(1);

        assertThat(
                developerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(EmployeeElement::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void validate_EmployeeWithDeveloper_FiledLevelIsEmpty() {
        List<EmployeeElement> developerElement = getAbstractEmployees(
                1,
                new Position[]{DEVELOPER},
                new int[]{100000},
                LEVEL_IS_EMPTY);

        employeeDataValidation.validate(developerElement);

        assertThat(developerElement.size())
                .isEqualTo(1);

        assertThat(
                developerElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(EmployeeElement::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void validate_EmployeeWithAnalytics_FiledTypeIsEmpty() {
        List<EmployeeElement> analyticsElement = getAbstractEmployees(
                1,
                new Position[]{ANALYTICS},
                new int[]{25000},
                TYPE_IS_EMPTY);

        employeeDataValidation.validate(analyticsElement);

        assertThat(analyticsElement.size())
                .isEqualTo(1);
        assertThat(
                analyticsElement.stream()
                        .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                        .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                        .map(EmployeeElement::getErrorRequiredMessage)
                        .collect(Collectors.joining()))
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, ANALYTICS.value()));
    }
}
