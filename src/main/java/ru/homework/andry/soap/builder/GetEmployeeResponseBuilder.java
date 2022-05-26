package ru.homework.andry.soap.builder;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import lombok.extern.slf4j.Slf4j;
import ru.homework.andry.soap.mapper.EmployeeSwitcherMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEmployeeResponseBuilder implements EmployeeResponseBuilder<GetEmployeesResponse> {

    private final EmployeeSwitcherMapper employeeSwitcherMapper;

    @Override  //todo в проекте нет GetEmployeesResponse
    public void build(GetEmployeesResponse getEmployeesResponse, List<EmployeeElement> employees) {
        log.info("Start generate GetEmployeesResponse");
        getEmployeesResponse.getEmployees()
                .addAll(employeeSwitcherMapper.elementsToEmployees(employees));

        getEmployeesResponse.setStatus(StatusResponseBuilder.build());
    }
}
