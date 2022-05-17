package ru.homework.andry.soap.model;

import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEmployee {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int salary;
    private Position position;

    public abstract boolean checkSalary();
}
