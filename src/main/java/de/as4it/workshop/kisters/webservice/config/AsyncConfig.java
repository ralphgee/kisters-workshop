package de.as4it.workshop.kisters.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "WebserviceAsyncExecutor")
    public Executor asyncExecutor(TaskExecutorBuilder builder) {
        ThreadPoolTaskExecutor executor = builder.maxPoolSize(10).corePoolSize(2).queueCapacity(500).threadNamePrefix("WebServiceAsync-").build();
        executor.initialize();
        return executor;
    }
}
