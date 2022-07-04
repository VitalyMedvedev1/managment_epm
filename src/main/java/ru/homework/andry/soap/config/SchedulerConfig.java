package ru.homework.andry.soap.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import ru.homework.andry.soap.constant.PropertiesValue;

import java.util.concurrent.Executor;

import static ru.homework.andry.soap.constant.PropertiesValue.*;

@Configuration
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class SchedulerConfig {
    private final PropertiesValue propertiesValue;

    @Bean("empExecutor")
    public Executor deleteEmployeeTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(propertiesValue.getCORE_POOL_SIZE()); //todo всегда ноль ? - нет из пропертей значение
        executor.setMaxPoolSize(propertiesValue.getMAX_POOL_SIZE()); //todo всегда ноль ? - нет из пропертей значение
        executor.setQueueCapacity(propertiesValue.getQUEUE_CAPACITY());  //todo всегда ноль ? - нет из пропертей значение
        executor.setThreadNamePrefix("Delete employee tread: ");
        executor.initialize();
        return executor;
    }
}
