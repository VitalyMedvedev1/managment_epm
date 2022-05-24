package ru.homework.andry.soap.model;

import org.junit.jupiter.api.Test;
import ru.homework.andry.soap.AbstractSpringContext;

import static org.assertj.core.api.Assertions.assertThat;

class AnalyticsElementTest extends AbstractSpringContext {

    private final AnalyticsElement analyticsElement = new AnalyticsElement();

    @Test
    void checkSalary_ValueInRange() {
        analyticsElement.setSalary(10100);

        assertThat(analyticsElement.checkSalary()).isTrue();
    }

    @Test
    void checkSalary_ValueNotInRange() {
        analyticsElement.setSalary(9000);

        assertThat(analyticsElement.checkSalary()).isFalse();
    }

    @Test
    void checkSalary_ValueInMinBorder() {
        analyticsElement.setSalary(10000);

        assertThat(analyticsElement.checkSalary()).isTrue();
    }

    @Test
    void checkSalary_ValueInMaxBorder() {
        analyticsElement.setSalary(35000);

        assertThat(analyticsElement.checkSalary()).isTrue();
    }
}