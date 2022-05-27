package ru.homework.andry.soap.web.controller;

import io.dliga.micro.employee_web_service.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.api.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }


    @GetMapping("/{position}")
    public List<Employee> findAllByPosition(@PathVariable String position) {
        return employeeService.findAllByPosition(position);
    }

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Employee> saveAll(@RequestBody List<EmployeeElement> employees) {
        return employeeService.saveAll(employees);
    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void saveAll(@PathVariable String login,
//                        @RequestBody List<NoteView> noteViews) {
//    }

//    @GetMapping
//    public List<AbstractEmployee> findByUserLogin() {
//        return employeesSOAPService.findAll();
//    }
//
//    @PutMapping("/note")
//    public void update(@PathVariable String login,
//                       @RequestBody NoteView noteView) {
//        noteService.save(login, noteView);
//    }
//
//    @PutMapping
//    public void updateAll(@PathVariable String login,
//                          @RequestBody List<NoteView> noteViews) {
//        noteService.saveAll(login, noteViews);
//    }
//
//    @DeleteMapping("/{notesId}")
//    public void deleteAll(@PathVariable String login, @PathVariable List<Long> notesId) {
//        noteService.deleteAll(notesId);
//    }
}