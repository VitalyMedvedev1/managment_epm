package ru.homework.andry.soap.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.api.kafka.EmployeeListener;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeListenerImpl implements EmployeeListener {

    private final EmployeeRepository employeeRepository;

    @Override
    @KafkaListener(
            topics = "${employee.upsert.message.topic.name}",
            groupId = "${spring.kafka.consumer.upsert.group}",
            containerFactory = "upsertListener")
    public void listenForUpsert(List<EmployeeEntity> entities) {
        log.info("Start upsert from kafka employees: {}", entities.toString());
        List<EmployeeEntity> savedEntities = employeeRepository.saveAll(entities);
        log.info("Successful upsert from kafka employee with ids: {}", getEntityIds(savedEntities));
    }

    @Override
    @KafkaListener(
            topics = "${employee.delete.message.topic.name}",
            groupId = "${spring.kafka.consumer.delete.group}",
            containerFactory = "deleteListener")
    public void listenForDelete(List<Long> ids) {
        log.info("Start delete from kafka employee with ids: {}", ids.toString());
        employeeRepository.deleteAllById(ids);
        log.info("Successful delete employees");
    }

    private List<Long> getEntityIds(List<EmployeeEntity> entities) {
        return entities.stream()
                       .map(EmployeeEntity::getId)
                       .collect(Collectors.toList());
    }
}
