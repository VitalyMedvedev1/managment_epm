package ru.homework.andry.soap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.homework.andry.soap.constant.PropertyValues;

import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class SchedulerConfig {

    private final PropertyValues propertyValues;

    @Bean("empExecutor")
    public Executor deleteEmployeeTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(propertyValues.getCORE_POOL_SIZE());
        executor.setMaxPoolSize(propertyValues.getMAX_POOL_SIZE());
        executor.setQueueCapacity(propertyValues.getQUEUE_CAPACITY());
        executor.setThreadNamePrefix("Delete employee tread: ");
        executor.initialize();
        return executor;
    }
}
