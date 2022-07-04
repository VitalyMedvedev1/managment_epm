package ru.homework.andry.soap.element.employee;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static ru.homework.andry.soap.constant.RepositoryValues.ANALYTICS_SALARY_RANGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("AnalyticsElement")
public class AnalyticsElement extends EmployeeElement {

    private String type;

    public AnalyticsElement(Long id,
                            String firstName,
                            String lastName,
                            int age,
                            int salary,
                            Position position,
                            String type) {
        super(id, firstName, lastName, age, salary, position);
        this.type = type;
    }

    @Override
    public boolean checkSalary() {
        return ANALYTICS_SALARY_RANGE.contains(getSalary());
    }

    @Override
    public boolean isBlankRequiredField() {
        return StringUtils.isEmpty(type);
    }
}