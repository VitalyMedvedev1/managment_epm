package ru.homework.andry.soap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.homework.andry.soap.constant.Values;

import java.util.concurrent.Executor;

import static ru.homework.andry.soap.constant.Values.*;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulingAsyncConfig {

    @Bean("empExecutor")
    public Executor initEmpExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix("MyAsync Thread - ");
        executor.initialize();
        return executor;
    }
}
