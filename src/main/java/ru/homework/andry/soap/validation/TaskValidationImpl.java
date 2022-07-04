package ru.homework.andry.soap.validation;

import io.dliga.micro.employee_web_service.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.homework.andry.soap.api.validation.TaskValidation;
import ru.homework.andry.soap.constant.PropertiesValue;
import ru.homework.andry.soap.entity.EmployeeEntity;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class TaskValidationImpl implements TaskValidation {

    private final PropertiesValue propertiesValue;

    @Override
    public boolean checkCountAssignTasks(int countRequest, EmployeeEntity employeeEntity) {
        return employeeEntity.getTasks().size() + countRequest
                <= Optional.ofNullable(propertiesValue.getMaxCountTask().get(employeeEntity.getPosition()))
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


