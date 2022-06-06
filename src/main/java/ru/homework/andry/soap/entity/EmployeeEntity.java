package ru.homework.andry.soap.entity;
//todo вынести пакет на уровень выше
// done
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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_s")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "salary", nullable = false)
    private int salary;
    @Column(name = "level", nullable = false)
    private String level;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "project", nullable = false)
    private String project;

    @Enumerated(EnumType.STRING)
    private Position position;
}
