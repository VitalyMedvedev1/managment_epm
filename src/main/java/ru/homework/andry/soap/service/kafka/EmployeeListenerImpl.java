package ru.homework.andry.soap.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.homework.andry.soap.api.kafka.EmployeeListener;
import ru.homework.andry.soap.entity.EmployeeEntity;
import ru.homework.andry.soap.repository.EmployeeRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeListenerImpl implements EmployeeListener {

    private final EmployeeRepository employeeRepository;

    @Override
    @KafkaListener(topics = "${employee.upsert.message.topic.name}", groupId = "${spring.kafka.consumer.upsert.group}", containerFactory = "upsertListener")
    public void listenForUpsert(List<EmployeeEntity> entities) {
        log.info("\n*********************************\n");
        employeeRepository.saveAll(entities);

    }

    @Override
    @KafkaListener(topics = "${employee.delete.message.topic.name}", groupId = "${spring.kafka.consumer.delete.group}", containerFactory = "deleteListener")
    public void listenForDelete(List<Long> ids) {
        log.info("\n*********************************\n");
        log.info(ids.toString());
    }
}
