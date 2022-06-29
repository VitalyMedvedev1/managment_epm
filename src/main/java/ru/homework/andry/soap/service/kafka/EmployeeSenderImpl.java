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

    private final KafkaTemplate<String, List<EmployeeEntity>> employeeUpsertKafkaTemplate;
    private final KafkaTemplate<String, List<Long>> employeeDeleteKafkaTemplate;
    private final String upsertTopic;
    private final String deleteTopic;

    public EmployeeSenderImpl(KafkaTemplate<String, List<EmployeeEntity>> employeeUpsertKafkaTemplate,
                              KafkaTemplate<String, List<Long>> employeeDeleteKafkaTemplate,
                              @Value("${employee.upsert.message.topic.name}") String upsertTopic,
                              @Value("${employee.delete.message.topic.name}") String deleteTopic) {

        this.employeeUpsertKafkaTemplate = employeeUpsertKafkaTemplate;
        this.employeeDeleteKafkaTemplate = employeeDeleteKafkaTemplate;
        this.upsertTopic = upsertTopic;
        this.deleteTopic = deleteTopic;
    }

    @Override
    public void sendToUpsert(List<EmployeeEntity> entities) {
        log.info("Sending employees: {} to upsert", entities.toString());
        employeeUpsertKafkaTemplate.send(upsertTopic, entities);
    }

    @Override
    public void sendToDelete(List<Long> ids) {
        log.info("Sending employee ids: {} to delete", ids.toString());
        employeeDeleteKafkaTemplate.send(deleteTopic, ids);
    }
}
