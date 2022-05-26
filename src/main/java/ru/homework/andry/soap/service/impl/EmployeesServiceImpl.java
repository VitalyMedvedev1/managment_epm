package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.builder.impl.CreateEmployeeResponseBuilder;
import ru.homework.andry.soap.builder.impl.GetEmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.service.EmployeeDataValidation;
import ru.homework.andry.soap.service.EmployeesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapperService employeeMapperService;
    private final EmployeeDataValidation employeeDataValidation;
    @SuppressWarnings("rawtypes")
    private final List<EmployeeResponseBuilder> responseBuilders;

    @Override
    public GetEmployeesResponse findAll() {
        log.info("Find all entity employees and map to elements");
        List<AbstractEmployee> abstractEmployees = employeeMapperService.entityToElement(employeeRepository.findAll());
        GetEmployeesResponse getEmployeesResponse = new GetEmployeesResponse();

        addResponseBody(abstractEmployees,
                getEmployeesResponse,
                responseBuilders.stream()
                        .filter(rb -> rb instanceof GetEmployeeResponseBuilder)
                        .findFirst().orElseThrow());

        return getEmployeesResponse;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private <T> void addResponseBody(List<AbstractEmployee> abstractEmployees,
                                     T employeesResponse,
                                     EmployeeResponseBuilder employeeResponseBuilder) {

        employeeResponseBuilder.build(employeesResponse, abstractEmployees);
    }

    @Override
    public CreateEmployeesResponse saveAll(CreateEmployeesRequest request) {
        List<AbstractEmployee> abstractEmployees =
                employeeDataValidation.validate(
                        map(request));

        save(getCorrectEmployee(abstractEmployees));

        CreateEmployeesResponse createEmployeesResponse = new CreateEmployeesResponse();
        addResponseBody(abstractEmployees,
                createEmployeesResponse,
                responseBuilders.stream()
                        .filter(rb -> rb instanceof CreateEmployeeResponseBuilder)
                        .findFirst().orElseThrow());

        return createEmployeesResponse;
    }

    private List<AbstractEmployee> map(CreateEmployeesRequest request) {
        log.info("Mapping employees from soap message to employeeElements");
        return employeeMapperService.employeesToElements(request.getEmployees());
    }

    private List<AbstractEmployee> getCorrectEmployee(List<AbstractEmployee> abstractEmployees) {
        log.info("Get correct employees");
        return abstractEmployees.stream()
                .filter(employee -> StringUtils.isBlank(employee.getErrorMessage()))
                .collect(Collectors.toList());
    }

    @Transactional
    void save(List<AbstractEmployee> employeesForSave) {
        log.info("Save employees");
        employeeRepository.saveAll(
                employeeMapperService.elementsToEntities(employeesForSave));
    }
}