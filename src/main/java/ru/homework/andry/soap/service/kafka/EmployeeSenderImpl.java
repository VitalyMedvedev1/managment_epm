package ru.homework.andry.soap.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.api.kafka.EmployeeSender;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

import static ru.homework.andry.soap.constant.AppValues.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeSenderImpl implements EmployeeSender {

    private final KafkaTemplate<String, EmployeeEntity> employeeCreateKafkaTemplate;
    private final KafkaTemplate<String, List<Long>> employeeDeleteKafkaTemplate;

    @Override
    public void sendToCreate(List<EmployeeEntity> entities) {
        log.info("Start sending employees to kafka");
        entities.forEach(entity -> {
            employeeCreateKafkaTemplate.send(KAFKA_UPSERT_TOPIC_NAME, String.valueOf(entity.getUuid()), entity);
            log.info("Sent employee with name: {}", entity.getFirstName() + " " + entity.getLastName());
        });
    }

    @Override
    public void sendToDelete(List<Long> ids) {
        log.info("Sending employee ids: {} to delete", ids.toString());
        employeeDeleteKafkaTemplate.send(KAFKA_DELETE_TOPIC_NAME, ids);
    }
}
