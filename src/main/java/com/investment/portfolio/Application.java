package com.investment.portfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableRetry
public class Application implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final ApplicationProperties applicationProperties;

    public Application(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Application started with properties: maxRequestsPerSecond={}, idempotencyKeyTtl={} seconds",
                applicationProperties.getMaxRequestsPerSecond(),
                applicationProperties.getIdempotencyKeyTtl());
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(applicationProperties.getThreadPoolCoreSize());
        executor.setMaxPoolSize(applicationProperties.getThreadPoolMaxSize());
        executor.setQueueCapacity(applicationProperties.getThreadPoolQueueCapacity());
        executor.setThreadNamePrefix("investor-request-");
        executor.initialize();
        return executor;
    }

    private void validateProperties() {
        if (applicationProperties.getMaxRequestsPerSecond() <= 0) {
            throw new IllegalStateException("maxRequestsPerSecond must be positive");
        }
        if (applicationProperties.getIdempotencyKeyTtl() <= 0) {
            throw new IllegalStateException("idempotencyKeyTtl must be positive");
        }
        if (applicationProperties.getThreadPoolCoreSize() <= 0 ||
            applicationProperties.getThreadPoolMaxSize() <= 0 ||
            applicationProperties.getThreadPoolQueueCapacity() <= 0) {
            throw new IllegalStateException("Thread pool configuration values must be positive");
        }
        if (applicationProperties.getThreadPoolMaxSize() < applicationProperties.getThreadPoolCoreSize()) {
            throw new IllegalStateException("maxPoolSize must be >= corePoolSize");
        }
    }
}
"