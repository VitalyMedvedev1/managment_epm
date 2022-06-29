package ru.homework.andry.soap.element.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dliga.micro.employee_web_service.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;

import static ru.homework.andry.soap.constant.PropertiesValue.REQUIRED_FIELD_ERROR_TEXT_MESSAGE;
import static ru.homework.andry.soap.constant.PropertiesValue.SALARY_ERROR_TEXT_MESSAGE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnalyticsElement.class, name = "AnalyticsElement"),
        @JsonSubTypes.Type(value = ManagerElement.class, name = "ManagerElement"),
        @JsonSubTypes.Type(value = DeveloperElement.class, name = "DeveloperElement") }
)
public abstract class EmployeeElement {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int salary;
    private String uuid;
    private Position position;

    @JsonIgnore
    private String errorIncorrectSalaryMessage;
    @JsonIgnore
    private String errorRequiredMessage;

    public EmployeeElement(Long id,
                           String firstName,
                           String lastName,
                           int age,
                           int salary,
                           Position position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    public void setErrorIncorrectSalaryMessage() {
        this.errorIncorrectSalaryMessage =
                MessageFormat.format(SALARY_ERROR_TEXT_MESSAGE, position.value());
    }

    public void setErrorRequiredMessage() {
        this.errorRequiredMessage =
                MessageFormat.format(REQUIRED_FIELD_ERROR_TEXT_MESSAGE, position.value());
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getErrorMessage() {
        return Optional.ofNullable(getErrorIncorrectSalaryMessage()).orElse("") +
                Optional.ofNullable(getErrorRequiredMessage()).orElse("");
    }

    public abstract boolean checkSalary();

    public abstract boolean isBlankRequiredField();
}
