package ru.homework.andry.soap.constant;

import lombok.Getter;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.homework.andry.soap.entity.EmployeeRestrictionsEntity;
import ru.homework.andry.soap.repository.EmployeeRestrictionsRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Getter
public class PropertiesValue {

    @Autowired
    private EmployeeRestrictionsRepository employeeRestrictionsRepository;

    @Value("${delete.emp.queue.capacity}")
    private int QUEUE_SIZE_FOR_DELETE_EMP;

    @Value("${delete.emp.tread.core.poll.size}")
    private int CORE_POOL_SIZE;

    @Value("${delete.emp.tread.max.poll.size}")
    private int MAX_POOL_SIZE;

    @Value("${delete.emp.tread.queue.capacity}")
    private int QUEUE_CAPACITY;

    public static Range<Integer> ANALYTICS_SALARY_RANGE;
    public static Range<Integer> DEVELOPER_SALARY_RANGE;
    public static Range<Integer> MANAGER_SALARY_RANGE;

    public static String SALARY_ERROR_TEXT_MESSAGE = "This salary is not suitable for position: {0}. ";
    public static String REQUIRED_FIELD_ERROR_TEXT_MESSAGE = "For position: {0}, required fields are not filled!";

    public static final int ERROR_CODE = 99;
    public static final String KAFKA_UPSERT_TOPIC_NAME = "employeesToUpsert";
    public static final String KAFKA_DELETE_TOPIC_NAME = "employeesToDelete";

    @PostConstruct
    void setEmployeeRestrictions() {
        List<EmployeeRestrictionsEntity> restrictions = employeeRestrictionsRepository.findAll();
        restrictions.forEach(this::setSalaryRestriction);
    }

    private void setSalaryRestriction(EmployeeRestrictionsEntity restriction) {
        switch (restriction.getPosition()){
            case MANAGER:
                MANAGER_SALARY_RANGE = getSalaryRange(restriction);
                break;
            case ANALYTICS:
                ANALYTICS_SALARY_RANGE = getSalaryRange(restriction);
                break;
            default:
                DEVELOPER_SALARY_RANGE = getSalaryRange(restriction);
                break;
        }
    }

    private Range<Integer> getSalaryRange(EmployeeRestrictionsEntity restriction) {
        return Range.between(restriction.getMin_salary(), restriction.getMax_salary());
    }
}
