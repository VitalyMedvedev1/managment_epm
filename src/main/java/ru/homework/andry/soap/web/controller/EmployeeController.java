package ru.homework.andry.soap.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.homework.andry.soap.element.employee.AbstractEmployee;
import ru.homework.andry.soap.api.service.EmployeeRESTService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRESTService employeeRESTService;

    @GetMapping
    public List<AbstractEmployee> findAll() {
        return employeeRESTService.findAll();
    }


    @GetMapping("/{position}")
    public List<AbstractEmployee> findAllByPosition(@PathVariable String position) {
        return employeeRESTService.findAllByPosition(position);
    }

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AbstractEmployee> saveAll(@RequestBody List<AbstractEmployee> employees) {
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