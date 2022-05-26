package ru.homework.andry.soap.model;

import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static ru.homework.andry.soap.constant.ValueConst.MANAGER_SALARY_RANGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ManagerElement extends AbstractEmployee {

    private String project;

    //todo сделай переносы
    // done
    public ManagerElement(Long id,
                          String firstName,
                          String lastName,
                          int age,
                          int salary,
                          Position position,
                          String project) {
        super(id, firstName, lastName, age, salary, position);
        this.project = project;
    }

    @Override
    public boolean checkSalary() {
        return MANAGER_SALARY_RANGE.contains(getSalary());
    }

    @Override
    public boolean isBlankRequiredField() {
        return StringUtils.isEmpty(project);
    }
}
