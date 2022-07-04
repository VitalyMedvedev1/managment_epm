package ru.homework.andry.soap.entity;

import io.dliga.micro.employee_web_service.Position;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee_restrictions", schema = "soap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(schema = "soap", name = "employee_restrictions_s", sequenceName = "employee_restrictions_s", allocationSize = 1)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmployeeRestrictionsEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_restrictions_s")
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

    @Column(name = "max_salary", nullable = false)
    private int max_salary;

    @Column(name = "min_salary", nullable = false)
    private int min_salary;

    @Column(name = "max_count_tasks", nullable = false)
    private int max_count_tasks;
}
