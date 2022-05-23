package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
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
    private final EmployeeMapper employeeMapper;
    private final EmployeeDataValidation employeeDataValidation;
    private final List<EmployeeResponseBuilder> responseBuilders;

    @Override
    @SuppressWarnings("unchecked")
    public GetEmployeesResponse findAll() {
        log.info("Find all entity employees and map to elements");
        List<AbstractEmployee> abstractEmployees = employeeMapper.entityToElement(employeeRepository.findAll());
        GetEmployeesResponse response = new GetEmployeesResponse();
        responseBuilders.stream()
                .filter(rb -> rb instanceof GetEmployeeResponseBuilder)
                .findFirst()
                .ifPresent(employeeResponseBuilder ->
                        employeeResponseBuilder.build(response, abstractEmployees));

        return response;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CreateEmployeesResponse saveAll(CreateEmployeesRequest request) {
        log.info("Mapping employees from soap message to employeeElements");
        List<AbstractEmployee> abstractEmployees =
                employeeDataValidation.validate(
                        employeeMapper.employeesToElements(
                                request.getEmployees()));

        log.info("Get correct employees");

        List<AbstractEmployee> employeesForSave =
                abstractEmployees.stream()
                        .filter(employee -> StringUtils.isBlank(employee.getErrorMessage()))
                        .collect(Collectors.toList());

        log.info("Get correct employees");
        employeeRepository.saveAll(
                employeeMapper.elementsToEntities(employeesForSave));

        CreateEmployeesResponse response = new CreateEmployeesResponse();
        responseBuilders.stream()
                .filter(rb -> rb instanceof CreateEmployeeResponseBuilder)
                .findFirst()
                .ifPresent(employeeResponseBuilder ->
                        employeeResponseBuilder.build(response, abstractEmployees));

        return response;
    }
}