package ru.homework.andry.soap.schedul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.homework.andry.soap.constant.PropertiesValue;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.homework.andry.soap.constant.PropertiesValue.QUEUE_SIZE_FOR_DELETE_EMP;
import static ru.homework.andry.soap.testdata.ValueConstTestData.getValues;

@ExtendWith(MockitoExtension.class)
class EmployeeSchedulerImplTest {

    public static final String QUEUE_SIZE_FOR_DELETE_EMP1 = "QUEUE_SIZE_FOR_DELETE_EMP";
    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeSchedulerImpl employeeScheduler = new EmployeeSchedulerImpl(employeeRepository);
    private final PropertiesValue propertiesValue = getValues();

    @BeforeEach
    void setCapacity() {
        Field field;
        try {
            field = propertiesValue.getClass().getDeclaredField(QUEUE_SIZE_FOR_DELETE_EMP1);
            field.setAccessible(true);
            field.setInt(propertiesValue, 2);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void execute() {
        when(employeeRepository.findAll(PageRequest.of(0, QUEUE_SIZE_FOR_DELETE_EMP)))
                .thenReturn(Page.empty());
        employeeScheduler.execute();
    }
}