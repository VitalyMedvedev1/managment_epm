package ru.homework.andry.soap.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


@Configuration
public class AppConfig {

    @ConfigurationProperties(prefix = "max.tasks.position")
    @Bean("countTasks")
    public Properties getMaxCountTaskByPosition() {
        return new Properties();
    }
}