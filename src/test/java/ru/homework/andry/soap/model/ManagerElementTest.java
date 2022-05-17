package ru.homework.andry.soap.model;

import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.AbstractSpringContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagerElementTest extends AbstractSpringContext {

    private ManagerElement managerElement = new ManagerElement();

    @Test
    void checkSalary_valueInRange() {
        managerElement.setSalary(160000);

        assertTrue(managerElement.checkSalary());
    }

    @Test
    void checkSalary_valueNotInRange() {
        managerElement.setSalary(100000);

        assertFalse(managerElement.checkSalary());
    }

    @Test
    void checkSalary_valueInMinBorder() {
        managerElement.setSalary(150000);

        assertTrue(managerElement.checkSalary());
    }

    @Test
    void checkSalary_valueInMaxBorder() {
        managerElement.setSalary(175000);

        assertTrue(managerElement.checkSalary());
    }
}