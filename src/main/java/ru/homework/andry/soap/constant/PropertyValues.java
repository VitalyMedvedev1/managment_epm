package ru.homework.andry.soap.constant;

import io.dliga.micro.employee_web_service.Position;
import lombok.Getter;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.homework.andry.soap.entity.EmployeeRestrictionsEntity;
import ru.homework.andry.soap.repository.EmployeeRestrictionsRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Getter
public class PropertyValues {

    @Value("${delete.emp.queue.capacity}")
    private int QUEUE_SIZE_FOR_DELETE_EMP;

    @Value("${delete.emp.tread.core.poll.size}")
    private int CORE_POOL_SIZE;

    @Value("${delete.emp.tread.max.poll.size}")
    private int MAX_POOL_SIZE;

    @Value("${delete.emp.tread.queue.capacity}")
    private int QUEUE_CAPACITY;
}
