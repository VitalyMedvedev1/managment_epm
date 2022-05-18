package ru.homework.andry.soap.model;

import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static ru.homework.andry.soap.constant.Values.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnalyticsElement extends AbstractEmployee {

    private String type;

    public AnalyticsElement(Long id, String firstName, String lastName, int age, int salary, Position position, String type) {
        super(id, firstName, lastName, age, salary, position);
        this.type = type;
    }

    @Override
    public boolean checkSalary() {
        return ANALYTICS_SALARY_RANGE.contains(getSalary());
    }

    @Override
    public boolean checkRequiredField() {
        return StringUtils.isNotEmpty(type);
    }
}
