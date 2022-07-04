package ru.homework.andry.soap.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks", schema = "soap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class TaskEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id; //todo отступ // done

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
