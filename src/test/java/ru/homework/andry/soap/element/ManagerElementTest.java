package ru.homework.andry.soap.element;

import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.AbstractSpringContext;
import ru.homework.andry.soap.element.employee.ManagerElement;

import static org.assertj.core.api.Assertions.assertThat;

class ManagerElementTest extends AbstractSpringContext {

    private final ManagerElement managerElement = new ManagerElement();

    @Test
    void checkSalary_ValueInRange() {
        managerElement.setSalary(160000);

        assertThat(managerElement.checkSalary()).isTrue();
    }

    @Test
    void checkSalary_ValueNotInRange() {
        managerElement.setSalary(100000);

        assertThat(managerElement.checkSalary()).isFalse();
    }

    @Test
    void checkSalary_ValueInMinBorder() {
        managerElement.setSalary(150000);

        assertThat(managerElement.checkSalary()).isTrue();
    }

    @Test
    void checkSalary_ValueInMaxBorder() {
        managerElement.setSalary(175000);

        assertThat(managerElement.checkSalary()).isTrue();
    }
}