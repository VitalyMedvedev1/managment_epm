package ru.homework.andry.soap.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.api.kafka.EmployeeSender;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;

@Service
@Slf4j
public class EmployeeSenderImpl implements EmployeeSender {

    private final KafkaTemplate<String, EmployeeEntity> employeeCreateKafkaTemplate;
    private final KafkaTemplate<String, List<Long>> employeeDeleteKafkaTemplate;
    private final String upsertTopic;
    private final String deleteTopic;

    public EmployeeSenderImpl(KafkaTemplate<String, EmployeeEntity> employeeCreateKafkaTemplate,
                              KafkaTemplate<String, List<Long>> employeeDeleteKafkaTemplate,
                              @Value("${employee.upsert.message.topic.name}") String upsertTopic,
                              @Value("${employee.delete.message.topic.name}") String deleteTopic) {

        this.employeeCreateKafkaTemplate = employeeCreateKafkaTemplate;
        this.employeeDeleteKafkaTemplate = employeeDeleteKafkaTemplate;
        this.upsertTopic = upsertTopic;
        this.deleteTopic = deleteTopic;
    }

    @Override
    public void sendToCreate(List<EmployeeEntity> entities) {
        log.info("Start sending employees to kafka");
        entities.forEach(entity -> {
            employeeCreateKafkaTemplate.send(upsertTopic, String.valueOf(entity.getUuid()), entity);
            log.info("Sent employee with name: {}", entity.getFirstName() + " " + entity.getLastName());
        });
    }

    @Override
    public void sendToDelete(List<Long> ids) {
        log.info("Sending employee ids: {} to delete", ids.toString());
        employeeDeleteKafkaTemplate.send(deleteTopic, ids);
    }
}
