package ru.homework.andry.soap.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.andry.soap.api.kafka.EmployeeListener;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import static ru.homework.andry.soap.constant.PropertiesValue.KAFKA_DELETE_TOPIC_NAME;
import static ru.homework.andry.soap.constant.PropertiesValue.KAFKA_UPSERT_TOPIC_NAME;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EmployeeListenerImpl implements EmployeeListener {

    private final EmployeeRepository employeeRepository;

    @Override
    @KafkaListener(
            topics = KAFKA_UPSERT_TOPIC_NAME,
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
            topics = KAFKA_DELETE_TOPIC_NAME,
            groupId = "${spring.kafka.consumer.delete.group}",
            containerFactory = "deleteListener")
    public void listenToDelete(List<Long> ids) {
        log.info("Start delete from kafka employee with ids: {}", ids.toString());

        employeeRepository.deleteAllById(ids);
        log.info("Successful delete employee by ids: {}", ids);
    }
}
