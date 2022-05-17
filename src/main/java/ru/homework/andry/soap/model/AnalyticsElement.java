package ru.homework.andry.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static ru.homework.andry.soap.constant.Values.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnalyticsElement extends AbstractEmployee {

    private String type;

    @Override
    public boolean checkSalary() {
        return ANALYTICS_SALARY_RANGE.contains(getSalary());
    }
}
