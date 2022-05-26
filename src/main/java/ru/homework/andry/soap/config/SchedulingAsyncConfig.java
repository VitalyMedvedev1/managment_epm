package ru.homework.andry.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static ru.homework.andry.soap.constant.ValueConst.*;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulingAsyncConfig {
    //todo не понимаю зачем это
    // done включение асинх шедулера + настройка тасков и пула, конечно в варианте когда он 1 пофиг, но на будующее

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
