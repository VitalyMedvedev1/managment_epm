package ru.homework.andry.soap.entity;

import io.dliga.micro.employee_web_service.Position;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employees", schema = "soap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(schema = "soap", name = "employee_s", sequenceName = "employee_s", allocationSize = 1)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmployeeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_s")
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "salary", nullable = false)
    private int salary;
    @Column(name = "level")
    private String level;
    @Column(name = "language")
    private String language;
    @Column(name = "type")
    private String type;
    @Column(name = "project")
    private String project;
    @Column(name = "uuid")
    private String uuid;

    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToMany(mappedBy = "employee")
    private Set<TaskEntity> tasks = new HashSet<>();
}
