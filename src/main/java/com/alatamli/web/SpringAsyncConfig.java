package com.alatamli.web;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
	
	@Bean(name = "threadPoolTaskExecutor")
	@Override
	public Executor getAsyncExecutor() {
		return new ThreadPoolTaskExecutor();
	}

}
