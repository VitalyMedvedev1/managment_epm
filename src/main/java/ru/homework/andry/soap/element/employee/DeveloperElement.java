package ru.homework.andry.soap.element.employee;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static ru.homework.andry.soap.constant.RepositoryValues.DEVELOPER_SALARY_RANGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("DeveloperElement")
public class DeveloperElement extends EmployeeElement {

    private String language;
    private String level;

    public DeveloperElement(Long id,
                            String firstName,
                            String lastName,
                            int age,
                            int salary,
                            Position position,
                            String language,
                            String level) {
        super(id, firstName, lastName, age, salary, position);
        this.language = language;
        this.level = level;
    }

    @Override
    public boolean checkSalary() {
        return DEVELOPER_SALARY_RANGE.contains(getSalary());
    }

    @Override
    public boolean isBlankRequiredField() {
        return StringUtils.isEmpty(language) || StringUtils.isEmpty(level);
    }
}
