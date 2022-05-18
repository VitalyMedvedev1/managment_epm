package ru.homework.andry.soap.repository.entity;

import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employees", schema = "soap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(schema = "soap", name = "employee_s", sequenceName = "employee_s", allocationSize = 1)
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_s")
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private int salary;
    private String level;
    private String language;
    private String type;
    private String project;

    @Enumerated(EnumType.STRING)
    private Position position;
}
