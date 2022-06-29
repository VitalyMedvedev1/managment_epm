package ru.homework.andry.soap.config.kafka;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
@EnableKafka
public class EmployeeProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, EmployeeEntity> producerUpsertFactory() {
        Map<String, Object> configProps = Map.of(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                                                 KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                                                 VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, EmployeeEntity> kafkaUpsertTemplate() {
        return new KafkaTemplate<>(producerUpsertFactory());
    }

    @Bean
    public ProducerFactory<String, List<Long>> producerDeleteFactory() {
        Map<String, Object> configProps = Map.of(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                                                 KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                                                 VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, List<Long>> kafkaDeleteTemplate() {
        return new KafkaTemplate<>(producerDeleteFactory());
    }
}
