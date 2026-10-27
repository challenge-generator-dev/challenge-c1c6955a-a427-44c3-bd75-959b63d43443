package com.investment.portfolio.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.MDC;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class LoggingConfig {

    @Value("${application.logging.level:INFO}")
    private String defaultLogLevel;

    @Value("${application.logging.pattern:%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n}")
    private String logPattern;

    @Value("${application.logging.file.path:logs/application.log}")
    private String logFilePath;

    @Value("${application.logging.file.max-size:10MB}")
    private String maxFileSize;

    @Value("${application.logging.file.max-history:30}")
    private int maxHistory;

    private static final String CORRELATION_ID_KEY = "correlationId";
    private static final String USER_ID_KEY = "userId";
    private static final String OPERATION_KEY = "operation";

    @PostConstruct
    public void initializeLogging() {
        System.setProperty("LOG_PATTERN", logPattern);
        System.setProperty("LOG_FILE", logFilePath);
        LoggerFactory.getLogger(LoggingConfig.class).info(
                "Logging initialized with level: {}, pattern: {}, file: {}",
                defaultLogLevel, logPattern, logFilePath
        );
    }

    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }

    public static class LoggingService {

        private final Logger logger = LoggerFactory.getLogger(LoggingService.class);

        public void logInfo(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.info(message);
            clearMDC();
        }

        public void logInfo(String message, Object... args) {
            logger.info(message, args);
        }

        public void logDebug(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.debug(message);
            clearMDC();
        }

        public void logDebug(String message, Object... args) {
            logger.debug(message, args);
        }

        public void logWarn(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.warn(message);
            clearMDC();
        }

        public void logWarn(String message, Object... args) {
            logger.warn(message, args);
        }

        public void logError(String message, Throwable throwable, Map<String, Object> context) {
            enrichMDC(context);
            logger.error(message, throwable);
            clearMDC();
        }

        public void logError(String message, Throwable throwable) {
            logger.error(message, throwable);
        }

        public void logError(String message, Map<String, Object> context) {
            enrichMDC(context);
            logger.error(message);
            clearMDC();
        }

        public String generateCorrelationId() {
            return UUID.randomUUID().toString();
        }

        public void setCorrelationId(String correlationId) {
            if (correlationId != null) {
                MDC.put(CORRELATION_ID_KEY, correlationId);
            }
        }

        public void setUserId(String userId) {
            if (userId != null) {
                MDC.put(USER_ID_KEY, userId);
            }
        }

        public void setOperation(String operation) {
            if (operation != null) {
                MDC.put(OPERATION_KEY, operation);
            }
        }

        public void clearCorrelationId() {
            MDC.remove(CORRELATION_ID_KEY);
        }

        public void clearUserId() {
            MDC.remove(USER_ID_KEY);
        }

        public void clearOperation() {
            MDC.remove(OPERATION_KEY);
        }

        private void enrichMDC(Map<String, Object> context) {
            if (context != null) {
                context.forEach((key, value) -> {
                    if (key != null && value != null) {
                        MDC.put(key, value.toString());
                    }
                });
            }
        }

        private void clearMDC() {
            MDC.remove(CORRELATION_ID_KEY);
            MDC.remove(USER_ID_KEY);
            MDC.remove(OPERATION_KEY);
        }

        public Map<String, String> getContext() {
            Map<String, String> context = new HashMap<>();
            String correlationId = MDC.get(CORRELATION_ID_KEY);
            String userId = MDC.get(USER_ID_KEY);
            String operation = MDC.get(OPERATION_KEY);
            
            if (correlationId != null) context.put(CORRELATION_ID_KEY, correlationId);
            if (userId != null) context.put(USER_ID_KEY, userId);
            if (operation != null) context.put(OPERATION_KEY, operation);
            
            return context;
        }

        public void logRequestReceived(String operationType, String investorId, String idempotencyKey) {
            Map<String, Object> context = new HashMap<>();
            context.put("event", "REQUEST_RECEIVED");
            context.put("operationType", operationType);
            context.put("investorId", investorId);
            context.put("idempotencyKey", idempotencyKey != null ? idempotencyKey : "N/A");
            
            logInfo("Investor request received", context);
        }

        public void logRequestProcessed(String operationType, String investorId, String requestId, long durationMs) {
            Map<String, Object> context = new HashMap<>();
            context.put("event", "REQUEST_PROCESSED");
            context.put("operationType", operationType);
            context.put("investorId", investorId);
            context.put("requestId", requestId);
            context.put("durationMs", durationMs);
            
            logInfo("Investor request processed successfully", context);
        }

        public void logRequestFailed(String operationType, String investorId, String errorMessage, Throwable throwable) {
            Map<String, Object> context = new HashMap<>();
            context.put("event", "REQUEST_FAILED");
            context.put("operationType", operationType);
            context.put("investorId", investorId);
            context.put("errorMessage", errorMessage);
            
            logError("Investor request failed: " + errorMessage, throwable, context);
        }
    }
}