package ru.homework.andry.soap.conroller.rest;

import io.dliga.micro.employee_web_service.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homework.andry.soap.model.employee.AbstractEmployee;
import ru.homework.andry.soap.service.EmployeeRESTService;
import ru.homework.andry.soap.service.EmployeesSOAPService;

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

//    @PostMapping("/note")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void save(@PathVariable String login,
//                     @RequestBody NoteView noteView) {
//    }
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