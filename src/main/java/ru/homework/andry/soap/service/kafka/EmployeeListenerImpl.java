package ru.homework.andry.soap.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.api.kafka.EmployeeListener;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

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
    public void listenToCreate(EmployeeEntity entity) {
        log.info("Start create new employee from kafka");

        Optional<EmployeeEntity> foundEmployee = employeeRepository.findByUuid(entity.getUuid());
        if (foundEmployee.isEmpty()) {
            EmployeeEntity savedEmployee = employeeRepository.save(entity);
            log.info("Successful create employee with id {} ", savedEmployee.getId());
        }
        else {
            log.info("Employee with uuid: {} already exists", foundEmployee.get().getUuid());
        }
    }

    @Override
    @KafkaListener(
            topics = "${employee.delete.message.topic.name}",
            groupId = "${spring.kafka.consumer.delete.group}",
            containerFactory = "deleteListener")
    public void listenToDelete(List<Long> ids) {
        log.info("Start delete from kafka employee with ids: {}", ids.toString());

        employeeRepository.deleteAllById(ids);
        log.info("Successful delete employee by ids: {}", ids);
    }
}
