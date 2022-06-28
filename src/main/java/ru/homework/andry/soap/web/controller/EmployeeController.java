package ru.homework.andry.soap.web.controller;

import io.dliga.micro.employee_web_service.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.homework.andry.soap.api.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> find() {
        return employeeService.findAll();
    }

    @GetMapping("/{position}")
    public List<Employee> findByPosition(@PathVariable String position) {
        return employeeService.findAllByPosition(position);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Employee> create(@RequestBody List<Employee> employees) {
        return employeeService.create(employees);
    }

    @PutMapping
    public List<Employee> update(@RequestBody List<Employee> employees) {
        return employeeService.update(employees);
    }

    @DeleteMapping("/{employeeIds}")
    public void delete(@PathVariable List<Long> employeeIds) {
        employeeService.delete(employeeIds);
    }

    @GetMapping("/producer/{message}")
    public void test(@PathVariable String message) {
        employeeService.sendMsg(message);
    }
}