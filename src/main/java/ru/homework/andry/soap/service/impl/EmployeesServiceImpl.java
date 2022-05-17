package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.builder.GetEmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.entity.EmployeeEntity;
import ru.homework.andry.soap.service.EmployeesService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final GetEmployeeResponseBuilder getEmployeeResponseBuilder;

    @Override
    public GetEmployeesResponse findAll() {
        log.info("Find all employees");
        List<Employee> employees = employeeMapper.employeesEntityToEmployees(employeeRepository.findAll());
        return getEmployeeResponseBuilder.build(employees);
    }

    @Override
    public CreateEmployeesResponse saveAll(CreateEmployeesRequest request) {
        log.info("Save all employees");
        List<EmployeeEntity> employees = employeeMapper.employeesToEmployeesEntity(request.getEmployees());
        //employeeRepository.saveAll(employees);
        CreateEmployeesResponse response = new CreateEmployeesResponse();
        Status responseStatus = new Status();
        responseStatus.setErrorCode(0);
        responseStatus.setErrorMessage("");
        response.getEmployees().addAll(employeeMapper.employeesEntityToEmployees(employees));
        response.setStatus(responseStatus);
        return response;
    }
}