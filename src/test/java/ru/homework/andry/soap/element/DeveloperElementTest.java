package ru.homework.andry.soap.element;

import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.AbstractSpringContext;
import ru.homework.andry.soap.element.employee.DeveloperElement;

import static org.assertj.core.api.Assertions.assertThat;

class DeveloperElementTest extends AbstractSpringContext {

    private final DeveloperElement developerElement = new DeveloperElement();

    @Test
    void checkSalary_ValueInRange() {
        developerElement.setSalary(70000);

        assertThat(developerElement.checkSalary()).isTrue();
    }

    @Test
    void checkSalary_ValueNotInRange() {
        developerElement.setSalary(49000);

        assertThat(developerElement.checkSalary()).isFalse();
    }

    @Test
    void checkSalary_ValueInMinBorder() {
        developerElement.setSalary(50000);

        assertThat(developerElement.checkSalary()).isTrue();
    }

    @Test
    void checkSalary_ValueInMaxBorder() {
        developerElement.setSalary(150000);

        assertThat(developerElement.checkSalary()).isTrue();
    }
}