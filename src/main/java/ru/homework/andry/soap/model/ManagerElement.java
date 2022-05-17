package ru.homework.andry.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static ru.homework.andry.soap.constant.Values.MANAGER_SALARY_RANGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManagerElement extends AbstractEmployee {

    private String project;

    @Override
    public boolean checkSalary() {
        return MANAGER_SALARY_RANGE.contains(getSalary());
    }
}
