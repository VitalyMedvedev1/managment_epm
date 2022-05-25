package ru.homework.andry.soap.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.exeption.BusinessLogicException;
import ru.homework.andry.soap.mapper.EmployeeMapper;
import ru.homework.andry.soap.model.employee.AbstractEmployee;
import ru.homework.andry.soap.repository.EmployeeRepository;
import ru.homework.andry.soap.service.EmployeeRESTService;

import java.util.List;

@Service("REST")
@RequiredArgsConstructor
@Slf4j
public class EmployeesRESTServiceImpl implements EmployeeRESTService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<AbstractEmployee> findAll() {
        List<EmployeeEntity> entities = employeeRepository.findAll();
        if (entities.isEmpty()) {
            throw new BusinessLogicException("Elements by position {0} did not find");
        }
        return employeeMapper.entityToElement(entities);
    }
}
