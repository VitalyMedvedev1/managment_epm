package ru.homework.andry.soap.service.schedul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.homework.andry.soap.constant.ValueConst;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.homework.andry.soap.constant.ValueConst.QUEUE_SIZE_FOR_DELETE_EMP;
import static ru.homework.andry.soap.testdata.ValueConstTestData.getValues;

@ExtendWith(MockitoExtension.class)
class EmployeeSchedulerImplTest {

    public static final String QUEUE_SIZE_FOR_DELETE_EMP1 = "QUEUE_SIZE_FOR_DELETE_EMP";
    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeSchedulerImpl employeeScheduler = new EmployeeSchedulerImpl(employeeRepository);
    private final ValueConst valueConst = getValues();

    @BeforeEach
    void setCapacity() {
        Field field;
        try {
            field = valueConst.getClass().getDeclaredField(QUEUE_SIZE_FOR_DELETE_EMP1);
            field.setAccessible(true);
            field.setInt(valueConst, 2);
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