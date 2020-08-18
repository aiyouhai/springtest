package com.tt.async;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@ComponentScan(basePackages="com.tt.async.*")
public class AsyncConfig {
    @Bean
	public Executor getExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(5);  
		taskExecutor.setMaxPoolSize(10);  
		taskExecutor.setQueueCapacity(25);  
		taskExecutor.initialize();  
        return taskExecutor;
    }
}
