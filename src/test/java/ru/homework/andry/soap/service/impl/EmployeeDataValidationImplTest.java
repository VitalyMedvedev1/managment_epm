package ru.homework.andry.soap.service.impl;

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
    private final Values values = new Values(10000, 35000, 50000, 150000, 150000, 175000);

    @Test
    void validate_TreeRowDeveloper_SalaryAndFieldCorrect() {
        List<AbstractEmployee> developerElements = getEmployeesTreeDeveloperElement(
                100000, LANGUAGE_1, DEVELOPER_LEVEL,
                110000, LANGUAGE_2, DEVELOPER_LEVEL,
                150000, LANGUAGE_3, DEVELOPER_LEVEL
        );

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElements);

        assertThat(developerElements.size()).isEqualTo(employees.size());
        assertThat(developerElements.stream()
                .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                .count()
        )
                .isEqualTo(employees.size());
    }

    @Test
    void validate_oneRowDeveloper_SalaryIncorrectRequiredFieldCorrect() {
        List<AbstractEmployee> developerElements = getEmployeesTreeDeveloperElement(
                100, LANGUAGE_1, DEVELOPER_LEVEL,
                110000, LANGUAGE_2, DEVELOPER_LEVEL,
                150000, LANGUAGE_3, DEVELOPER_LEVEL
        );

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElements);

        assertThat(employees.size()).isEqualTo(3);
        assertThat(employees.size()).isEqualTo(3);
        assertThat(developerElements.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                .count()
        )
                .isEqualTo(1);


        assertThat(developerElements.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                .map(AbstractEmployee::getErrorIncorrectSalaryMessage)
                .collect(Collectors.joining())
        )
                .isEqualTo(SALARY_ERROR_TEXT_MESSAGE + DEVELOPER.value());
    }

    @Test
    void validate_oneRowManager_SalaryIncorrectRequiredFieldCorrect() {
        List<AbstractEmployee> managerElement = getEmployeeManagerElement(1, PROJECT);

        List<AbstractEmployee> employees = employeeDataValidation.validate(managerElement);

        assertThat(employees.size()).isEqualTo(managerElement.size());
        assertThat(managerElement.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorIncorrectSalaryMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorRequiredMessage()))
                .map(AbstractEmployee::getErrorIncorrectSalaryMessage)
                .collect(Collectors.joining())
        )
                .isEqualTo(SALARY_ERROR_TEXT_MESSAGE + MANAGER.value());
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowManager_RequiredFiledProjectNameEmpty() {
        List<AbstractEmployee> managerElement = getEmployeeManagerElement(160000, "");

        List<AbstractEmployee> employees = employeeDataValidation.validate(managerElement);

        assertThat(employees.size()).isEqualTo(managerElement.size());

        assertThat(managerElement.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                .map(AbstractEmployee::getErrorRequiredMessage)
                .collect(Collectors.joining())
        )
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, MANAGER.value()));
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowDeveloper_RequiredFiledLanguageEmpty() {
        List<AbstractEmployee> developerElement = getEmployeeDeveloperElement(140000, "", "middle");

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElement);

        assertThat(employees.size()).isEqualTo(developerElement.size());
        assertThat(developerElement.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                .map(AbstractEmployee::getErrorRequiredMessage)
                .collect(Collectors.joining())
        )
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowDeveloper_RequiredFiledLevelEmpty() {
        List<AbstractEmployee> developerElement = getEmployeeDeveloperElement(140000, "java", "");

        List<AbstractEmployee> employees = employeeDataValidation.validate(developerElement);

        assertThat(employees.size()).isEqualTo(developerElement.size());
        assertThat(developerElement.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                .map(AbstractEmployee::getErrorRequiredMessage)
                .collect(Collectors.joining())
        )
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowAnalytics_RequiredFiledTypeEmpty() {
        List<AbstractEmployee> analyticsElement = getEmployeeAnalyticsElement(30000, "");

        List<AbstractEmployee> employees = employeeDataValidation.validate(analyticsElement);

        assertThat(employees.size()).isEqualTo(analyticsElement.size());
        assertThat(analyticsElement.stream()
                .filter(emp -> StringUtils.isNotEmpty(emp.getErrorRequiredMessage()))
                .filter(emp -> StringUtils.isEmpty(emp.getErrorIncorrectSalaryMessage()))
                .map(AbstractEmployee::getErrorRequiredMessage)
                .collect(Collectors.joining())
        )
                .isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, ANALYTICS.value()));    }
}
