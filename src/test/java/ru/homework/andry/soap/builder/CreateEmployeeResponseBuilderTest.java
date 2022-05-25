package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.Position;
import io.dliga.micro.employee_web_service.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mapstruct.factory.Mappers;
import ru.homework.andry.soap.builder.impl.CreateEmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.employee.AbstractEmployee;

import java.util.List;

import static io.dliga.micro.employee_web_service.Position.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.homework.andry.soap.testdata.EmployeesTestData.*;

class CreateEmployeeResponseBuilderTest {

    private final CreateEmployeeResponseBuilder responseBuilder =
            new CreateEmployeeResponseBuilder(Mappers.getMapper(EmployeeMapper.class));

    private final CreateEmployeesResponse response = new CreateEmployeesResponse();

    @AfterEach
    void clearResponse() {
        response.getEmployees().clear();
    }

    @Test
    void build_EmployeesWithCorrectRows() {
        List<AbstractEmployee> elements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, MANAGER, ANALYTICS},
                        new int[]{1111, 2222, 3333},
                        "");

        responseBuilder.build(response, elements);

        assertThat(
                response.getEmployees().stream()
                        .filter(employee -> {
                            Status status = employee.getStatus();
                            return !(status.getErrorCode() == 0 && StringUtils.isBlank(status.getErrorMessage()));
                        }))
                .isEmpty();

        assertThat(response.getEmployees().size())
                .isEqualTo(elements.size());
    }

    @Test
    void build_EmployeesWithCorrectRowAndIncorrectRow_ResponseWithOneErrorAndOneCorrect() {
        List<AbstractEmployee> elements =
                getAbstractEmployees(
                        2,
                        new Position[]{ANALYTICS, MANAGER},
                        new int[]{1111, 2222},
                        ERROR_ROW_ANALYTICS);

        responseBuilder.build(response, elements);

        assertThat(
                response.getEmployees().stream()
                        .filter(employee -> {
                            Status status = employee.getStatus();
                            return !(status.getErrorCode() == 0 && StringUtils.isBlank(status.getErrorMessage()));
                        }).count())
                .isEqualTo(1);

        assertThat(
                response.getEmployees().stream()
                        .filter(employee -> {
                            Status status = employee.getStatus();
                            return status.getErrorCode() == 0 && StringUtils.isBlank(status.getErrorMessage());
                        }).count())
                .isEqualTo(1);

        assertThat(response.getEmployees().size())
                .isEqualTo(elements.size());
    }

    @Test
    void build_EmployeesWithIncorrectRows() {
        List<AbstractEmployee> elements =
                getAbstractEmployees(
                        3,
                        new Position[]{DEVELOPER, MANAGER, ANALYTICS},
                        new int[]{1111, 2222, 3333},
                        ERROR_ALL_ROWS);

        responseBuilder.build(response, elements);

        assertThat(
                response.getEmployees().stream()
                        .filter(employee -> {
                            Status status = employee.getStatus();
                            return status.getErrorCode() == 99 && StringUtils.isNotBlank(status.getErrorMessage());
                        }).count())
                .isEqualTo(3);

        assertThat(response.getEmployees().size())
                .isEqualTo(elements.size());
    }
}