package ru.homework.andry.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static ru.homework.andry.soap.constant.PropertiesValue.*;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulerConfig {

    @Bean("empExecutor")
    public Executor deleteEmployeeTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE); //todo всегда ноль ? - нет из пропертей значение
        executor.setMaxPoolSize(MAX_POOL_SIZE); //todo всегда ноль ? - нет из пропертей значение
        executor.setQueueCapacity(QUEUE_CAPACITY);  //todo всегда ноль ? - нет из пропертей значение
        executor.setThreadNamePrefix("Delete employee tread: ");
        executor.initialize();
        return executor;
    }
}
