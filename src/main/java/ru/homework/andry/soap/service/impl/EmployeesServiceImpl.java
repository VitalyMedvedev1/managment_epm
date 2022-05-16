package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.GetEmployeesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.repository.entity.Employee;
import ru.homework.andry.soap.service.EmployeesService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public GetEmployeesResponse findAll() {
        log.info("Find all employees");
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.employeesToGetEmployeesResponse(employees);
    }
}