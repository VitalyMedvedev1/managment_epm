package ru.homework.andry.soap.validation;

import io.dliga.micro.employee_web_service.Position;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.validation.TaskValidation;
import ru.homework.andry.soap.entity.EmployeeEntity;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
@Slf4j
public class TaskValidationImpl implements TaskValidation {

    @Autowired
    @Qualifier("countTasks")
    private Properties properties;
    private Map<Position, Integer> maxCountTask = new HashMap<>();

    @PostConstruct
    private void initMaxCountTask() {

        maxCountTask = Arrays.stream(Position.values())
                             .collect(Collectors.toMap(p -> p,
                                                       this::getMaxCountTasks,
                                                       (prev, next) -> next,
                                                       HashMap::new));
    }

    private int getMaxCountTasks(Position position) {
        return Integer.parseInt(properties.getProperty(position.name()
                                                               .toLowerCase(Locale.ROOT)));
    }

    @Override
    public boolean checkCountAssignTasks(int countRequest, EmployeeEntity employeeEntity) {
        return employeeEntity.getTasks().size() + countRequest
                <= Optional.ofNullable(maxCountTask.get(employeeEntity.getPosition()))
                           .orElse(0);
    }

    @Override
    public List<UUID> validate(List<UUID> requestIds, List<UUID> foundIds) {
        log.info("Start validate request tasks with ids: {}, found tasks ids: {}", requestIds, foundIds);

        List<UUID> notFoundIds = getNotFoundIds(requestIds, foundIds);
        log.info("Successful validate request tasks with ids: {}, found tasks ids: {}", requestIds, foundIds);
        return notFoundIds;
    }

    private List<UUID> getNotFoundIds(List<UUID> requestIds, List<UUID> foundIds) {
        return requestIds.stream()
                         .filter(it -> !foundIds.contains(it))
                         .collect(Collectors.toList());
    }
}


