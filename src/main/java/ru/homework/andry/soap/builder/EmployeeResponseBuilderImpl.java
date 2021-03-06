package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.CreateEmployeesResponse;
import io.dliga.micro.employee_web_service.Employee;
import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import io.dliga.micro.employee_web_service.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.builder.EmployeeResponseBuilder;

import java.util.List;

import static ru.homework.andry.soap.constant.AppValues.ERROR_CODE;

@Component
@Slf4j
public class EmployeeResponseBuilderImpl implements EmployeeResponseBuilder {

    @Override
    public GetEmployeesResponse buildGetEmployeesResponse(List<Employee> employees) {
        log.info("Start generate GetEmployeesResponse");
        GetEmployeesResponse getEmployeesResponse = new GetEmployeesResponse();
        getEmployeesResponse.getEmployees().addAll(employees);
        getEmployeesResponse.setStatus(StatusResponseBuilder.build());

        return getEmployeesResponse;
    }

    @Override
    public CreateEmployeesResponse buildCreateEmployeesResponse(List<Employee> employees) {
        CreateEmployeesResponse createEmployeesResponse = new CreateEmployeesResponse();
        employees.forEach(element -> {
                    element.setStatus(getResponseStatus(element.getErrorMessage()));
                    createEmployeesResponse.getEmployees().add(element);
                });
        return createEmployeesResponse;
    }

    private Status getResponseStatus(String errorMessage) {
        log.debug("Start generate StatusResponse");
        if (StringUtils.isNotBlank(errorMessage)) {
            return StatusResponseBuilder.build(ERROR_CODE, errorMessage);
        } else {
            return StatusResponseBuilder.build();
        }
    }
}
