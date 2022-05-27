package ru.homework.andry.soap.service;

import io.dliga.micro.employee_web_service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.api.builder.EmployeeResponseBuilder;
import ru.homework.andry.soap.builder.CreateEmployeeResponseBuilder;
import ru.homework.andry.soap.builder.GetEmployeeResponseBuilder;
import ru.homework.andry.soap.element.EmployeeElement;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.api.service.EmployeeDataValidation;
import ru.homework.andry.soap.api.service.EmployeesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapperServiceImpl employeeMapperServiceImpl; //todo почему не через интерфейс ?
    private final EmployeeDataValidation employeeDataValidation;
    @SuppressWarnings("rawtypes")
    private final List<EmployeeResponseBuilder> responseBuilders;

    @Override
    public GetEmployeesResponse findAll() {
        log.info("Find all entity employees and map to elements");
        List<EmployeeElement> employeeElements = employeeMapperServiceImpl.entityToElement(employeeRepository.findAll());
        GetEmployeesResponse getEmployeesResponse = new GetEmployeesResponse();

        addResponseBody(employeeElements,
                getEmployeesResponse,
                responseBuilders.stream()
                        .filter(rb -> rb instanceof GetEmployeeResponseBuilder)
                        .findFirst().orElseThrow());

        return getEmployeesResponse;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private <T> void addResponseBody(List<EmployeeElement> employeeElements,
                                     T employeesResponse,
                                     EmployeeResponseBuilder employeeResponseBuilder) {

        employeeResponseBuilder.build(employeesResponse, employeeElements);
    }

    @Override
    public CreateEmployeesResponse saveAll(CreateEmployeesRequest request) {
        List<EmployeeElement> employeeElements =
                employeeDataValidation.validate(
                        map(request));

        save(getCorrectEmployee(employeeElements));

        CreateEmployeesResponse createEmployeesResponse = new CreateEmployeesResponse();
        addResponseBody(employeeElements,
                createEmployeesResponse,
                responseBuilders.stream()
                        .filter(rb -> rb instanceof CreateEmployeeResponseBuilder)
                        .findFirst().orElseThrow());

        return createEmployeesResponse;
    }

    private List<EmployeeElement> map(CreateEmployeesRequest request) {
        log.info("Mapping employees from soap message to employeeElements");
        return employeeMapperServiceImpl.employeesToElements(request.getEmployees());
    }

    private List<EmployeeElement> getCorrectEmployee(List<EmployeeElement> employeeElements) {
        log.info("Get correct employees");
        return employeeElements.stream()
                .filter(employee -> StringUtils.isBlank(employee.getErrorMessage()))
                .collect(Collectors.toList());
    }

    @Transactional
    void save(List<EmployeeElement> employeesForSave) {
        log.info("Save employees");
        employeeRepository.saveAll(
                employeeMapperServiceImpl.elementsToEntities(employeesForSave));
    }
}