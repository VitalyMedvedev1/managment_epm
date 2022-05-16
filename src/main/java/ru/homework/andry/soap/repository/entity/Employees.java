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
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_s")
    private Long id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;
}
