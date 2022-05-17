package ru.homework.andry.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static ru.homework.andry.soap.constant.Values.DEVELOPER_SALARY_RANGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeveloperElement extends AbstractEmployee {

    private String language;
    private String level;

    @Override
    public boolean checkSalary() {
        return DEVELOPER_SALARY_RANGE.contains(getSalary());
    }
}
