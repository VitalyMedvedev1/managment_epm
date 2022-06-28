package ru.homework.andry.soap.config.kafka;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.homework.andry.soap.element.employee.EmployeeElement;
import ru.homework.andry.soap.entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@EnableKafka
@Configuration
public class EmployeeConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.consumer.upsert.group}")
    private String upsertGroup;

    @Value("${spring.kafka.consumer.delete.group}")
    private String deleteGroup;

    @Bean
    public ConsumerFactory<String, List<EmployeeEntity>> forUpsertConsumerFactory() {
        Map<String, Object> props = Map.of(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                                           GROUP_ID_CONFIG, upsertGroup,
                                           KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                                           VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructParametricType(List.class, EmployeeEntity.class);
        return new DefaultKafkaConsumerFactory<>(props,
                                                 new StringDeserializer(),
                                                 new JsonDeserializer<>(type, om, false));
    }

    @Bean("upsertListener")
    public ConcurrentKafkaListenerContainerFactory<String, List<EmployeeEntity>> kafkaListenerForUpsertContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, List<EmployeeEntity>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(forUpsertConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, List<Long>> forDeleteConsumerFactory() {
        Map<String, Object> props = Map.of(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                                           GROUP_ID_CONFIG, deleteGroup,
                                           KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                                           VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        ObjectMapper om = new ObjectMapper();
        JavaType type = om.getTypeFactory().constructParametricType(List.class, Long.class);
        return new DefaultKafkaConsumerFactory<>(props,
                                                 new StringDeserializer(),
                                                 new JsonDeserializer<>(type, om, false));
    }

    @Bean("deleteListener")
    public ConcurrentKafkaListenerContainerFactory<String, List<Long>> kafkaListenerForDeleteContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, List<Long>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(forDeleteConsumerFactory());
        return factory;
    }
}