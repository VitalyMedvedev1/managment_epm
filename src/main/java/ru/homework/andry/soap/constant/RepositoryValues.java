package ru.homework.andry.soap.constant;

import io.dliga.micro.employee_web_service.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.entity.EmployeeRestrictionsEntity;
import ru.homework.andry.soap.repository.EmployeeRestrictionsRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@RequiredArgsConstructor
public class RepositoryValues {

    private final EmployeeRestrictionsRepository employeeRestrictionsRepository;

    public static Range<Integer> ANALYTICS_SALARY_RANGE;
    public static Range<Integer> DEVELOPER_SALARY_RANGE;
    public static Range<Integer> MANAGER_SALARY_RANGE;

    private Map<Position, Integer> maxCountTask = new HashMap<>();

    @PostConstruct
    void setEmployeeRestrictions() {
        List<EmployeeRestrictionsEntity> restrictions = employeeRestrictionsRepository.findAll();
        restrictions.forEach(this::setSalaryRestriction);
        restrictions.forEach(
                rest -> setMaxCountTasksRestriction(rest.getPosition(), rest.getMax_count_tasks()));
    }

    private void setSalaryRestriction(EmployeeRestrictionsEntity restriction) {
        switch (restriction.getPosition()) {
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

    private void setMaxCountTasksRestriction(Position position, int taskCount) {
        maxCountTask.put(position, taskCount);
    }
}
