package ru.homework.andry.soap.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.api.service.EmployeeRESTService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRESTService employeeRESTService;

    @GetMapping
    public List<EmployeeElement> findAll() {
        return employeeRESTService.findAll();
    }


    @GetMapping("/{position}")
    public List<EmployeeElement> findAllByPosition(@PathVariable String position) {
        return employeeRESTService.findAllByPosition(position);
    }

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public List<EmployeeElement> saveAll(@RequestBody List<EmployeeElement> employees) {
        return employeeRESTService.saveAll(employees);
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