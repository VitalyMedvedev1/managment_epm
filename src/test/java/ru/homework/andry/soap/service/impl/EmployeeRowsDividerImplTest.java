package ru.homework.andry.soap.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.andry.soap.constant.Values;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.service.EmployeeRowsDivider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import static io.dliga.micro.employee_web_service.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.constant.Values.REQUIRED_FIELD_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.constant.Values.SALARY_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRowsDividerImplTest {

    private final EmployeeRowsDivider employeeRowsDivider = new EmployeeRowsDividerImpl();
    private final Values values = new Values(10000, 35000, 50000, 150000, 150000, 175000);

    @Test
    void divideOnCorrectAndIncorrect_TreeRowDeveloper_SalaryCorrect() {
        List<AbstractEmployee> developerElements = getEmployeesTreeDeveloperElement(
                100000, LANGUAGE_1, DEVELOPER_LEVEL,
                110000, LANGUAGE_2, DEVELOPER_LEVEL,
                150000, LANGUAGE_3, DEVELOPER_LEVEL
        );

        Map<Boolean, List<AbstractEmployee>> employeesByCorrectData = employeeRowsDivider.divideOnCorrectAndIncorrect(developerElements);

        assertThat(developerElements.size()).isEqualTo(employeesByCorrectData.get(true).size());
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowDeveloper_SalaryIncorrect() {
        List<AbstractEmployee> developerElements = getEmployeesTreeDeveloperElement(
                100, LANGUAGE_1, DEVELOPER_LEVEL,
                110000, LANGUAGE_2, DEVELOPER_LEVEL,
                150000, LANGUAGE_3, DEVELOPER_LEVEL
        );

        Map<Boolean, List<AbstractEmployee>> employeesDivideByFieldData = employeeRowsDivider.divideOnCorrectAndIncorrect(developerElements);

        assertThat(employeesDivideByFieldData.get(true).size()).isEqualTo(2);
        assertThat(employeesDivideByFieldData.get(false).size()).isEqualTo(1);
        assertThat(SALARY_ERROR_TEXT_MESSAGE + DEVELOPER.value()).isEqualTo(employeesDivideByFieldData.get(false).get(0).getErrorIncorrectSalaryMessage());
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowManager_SalaryIncorrect() {
        List<AbstractEmployee> managerElement = getEmployeeManagerElement(1, PROJECT);

        Map<Boolean, List<AbstractEmployee>> employeesDivideByFieldData = employeeRowsDivider.divideOnCorrectAndIncorrect(managerElement);

        assertThat(employeesDivideByFieldData.get(true).size()).isEqualTo(0);
        assertThat(employeesDivideByFieldData.get(false).size()).isEqualTo(1);
        assertThat(SALARY_ERROR_TEXT_MESSAGE + MANAGER.value()).isEqualTo(employeesDivideByFieldData.get(false).get(0).getErrorIncorrectSalaryMessage());
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowManager_RequiredFiledProjectNameEmpty() {
        List<AbstractEmployee> managerElement = getEmployeeManagerElement(160000, "");

        Map<Boolean, List<AbstractEmployee>> correctAndIncorrectEmployee = employeeRowsDivider.divideOnCorrectAndIncorrect(managerElement);

        assertThat(correctAndIncorrectEmployee.get(true).size()).isEqualTo(0);
        assertThat(correctAndIncorrectEmployee.get(false).size()).isEqualTo(1);
        assertThat(correctAndIncorrectEmployee.get(false).get(0).getErrorRequiredMessage()).isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, MANAGER.value()));
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowDeveloper_RequiredFiledLanguageEmpty() {
        List<AbstractEmployee> developer = getEmployeeDeveloperElement(160000, "", "middle");

        Map<Boolean, List<AbstractEmployee>> correctAndIncorrectEmployee = employeeRowsDivider.divideOnCorrectAndIncorrect(developer);

        assertThat(correctAndIncorrectEmployee.get(true).size()).isEqualTo(0);
        assertThat(correctAndIncorrectEmployee.get(false).size()).isEqualTo(1);
        assertThat(correctAndIncorrectEmployee.get(false).get(0).getErrorRequiredMessage()).isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowDeveloper_RequiredFiledLevelEmpty() {
        List<AbstractEmployee> developer = getEmployeeDeveloperElement(160000, "java", "");

        Map<Boolean, List<AbstractEmployee>> correctAndIncorrectEmployee = employeeRowsDivider.divideOnCorrectAndIncorrect(developer);

        assertThat(correctAndIncorrectEmployee.get(true).size()).isEqualTo(0);
        assertThat(correctAndIncorrectEmployee.get(false).size()).isEqualTo(1);
        assertThat(correctAndIncorrectEmployee.get(false).get(0).getErrorRequiredMessage()).isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, DEVELOPER.value()));
    }

    @Test
    void divideOnCorrectAndIncorrect_oneRowAnalytics_RequiredFiledTypeEmpty() {
        List<AbstractEmployee> analytics = getEmployeeAnalyticsElement(160000, "");

        Map<Boolean, List<AbstractEmployee>> correctAndIncorrectEmployee = employeeRowsDivider.divideOnCorrectAndIncorrect(analytics);

        assertThat(correctAndIncorrectEmployee.get(true).size()).isEqualTo(0);
        assertThat(correctAndIncorrectEmployee.get(false).size()).isEqualTo(1);
        assertThat(correctAndIncorrectEmployee.get(false).get(0).getErrorRequiredMessage()).isEqualTo(MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, ANALYTICS.value()));
    }
}