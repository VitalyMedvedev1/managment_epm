package ru.homework.andry.soap.model;

import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.AbstractSpringContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeveloperElementTest extends AbstractSpringContext {

    private DeveloperElement developerElement = new DeveloperElement();

    @Test
    void checkSalary_valueInRange() {
        developerElement.setSalary(70000);

        assertTrue(developerElement.checkSalary());
    }

    @Test
    void checkSalary_valueNotInRange() {
        developerElement.setSalary(49000);

        assertFalse(developerElement.checkSalary());
    }

    @Test
    void checkSalary_valueInMinBorder() {
        developerElement.setSalary(50000);

        assertTrue(developerElement.checkSalary());
    }

    @Test
    void checkSalary_valueInMaxBorder() {
        developerElement.setSalary(150000);

        assertTrue(developerElement.checkSalary());
    }
}