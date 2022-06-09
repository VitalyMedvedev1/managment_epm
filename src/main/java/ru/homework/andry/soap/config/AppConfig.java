package ru.homework.andry.soap.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;


@Configuration
public class AppConfig {

    @ConfigurationProperties(prefix = "max.tasks.position")
    @Bean("countTasks")
    public Properties getMaxCountTaskByPosition() {
        return new Properties();
    }
}