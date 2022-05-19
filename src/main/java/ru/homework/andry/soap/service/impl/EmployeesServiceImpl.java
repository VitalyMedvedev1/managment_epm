package ru.homework.andry.soap.service.impl;

import io.dliga.micro.employee_web_service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.builder.GetEmployeeResponseBuilder;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.AbstractEmployee;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.service.EmployeeDataValidation;
import ru.homework.andry.soap.service.EmployeesService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final GetEmployeeResponseBuilder getEmployeeResponseBuilder;
    private final EmployeeDataValidation employeeDataValidation;

    @Override
    public GetEmployeesResponse findAll() {
        log.info("Find all employees");
        List<Employee> employees = employeeMapper.entityToEmployeeSoapMsg(employeeRepository.findAll());
        return getEmployeeResponseBuilder.build(employees);
    }

/*    @Override
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
    }*/

    @Override
    public CreateEmployeesResponse saveAll(CreateEmployeesRequest request) {
        log.info("Mapping employees from soap message to employeesElement");
//        List<AbstractEmployee> employeeElements = employeeMapper.employeesSoapMsgElements(request.getEmployees());
//        Map<Boolean, List<AbstractEmployee>> correctAndIncorrectRowEmployees = employeeDataValidation.divideOnCorrectAndIncorrect(employeeElements);
//        List<AbstractEmployee> correctRowEmployees = correctAndIncorrectRowEmployees.get(true);
//        List<AbstractEmployee> incorrectRowEmployees = correctAndIncorrectRowEmployees.get(false);
//        if (!correctRowEmployees.isEmpty()) {
//            //employeeRepository.saveAll()
//        }
        return null;
    }

}