package ru.homework.andry.soap.model;

import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.AbstractSpringContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyticsElementTest extends AbstractSpringContext {

    private AnalyticsElement analyticsElement = new AnalyticsElement();

    @Test
    void checkSalary_valueInRange() {
        analyticsElement.setSalary(10100);

        assertTrue(analyticsElement.checkSalary());
    }

    @Test
    void checkSalary_valueNotInRange() {
        analyticsElement.setSalary(9000);

        assertFalse(analyticsElement.checkSalary());
    }

    @Test
    void checkSalary_valueInMinBorder() {
        analyticsElement.setSalary(10000);

        assertTrue(analyticsElement.checkSalary());
    }

    @Test
    void checkSalary_valueInMaxBorder() {
        analyticsElement.setSalary(35000);

        assertTrue(analyticsElement.checkSalary());
    }
}