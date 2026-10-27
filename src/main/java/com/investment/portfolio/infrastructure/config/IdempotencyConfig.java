package com.investment.portfolio.infrastructure.config;




import com.investment.portfolio.domain.model.RequestStatus;
import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
@EnableCaching
public class IdempotencyConfig {

    @Bean
    public CacheManager idempotencyCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(java.util.List.of("idempotency-keys"));
        return cacheManager;
    }

    @Component
    @Primary
    public static class IdempotencyService {

        private final ConcurrentHashMap<String, IdempotencyRecord> idempotencyStore = new ConcurrentHashMap<>();
        private final com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort persistencePort;
        private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(IdempotencyService.class);

        public IdempotencyService(com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort persistencePort) {
            this.persistencePort = persistencePort;
        }

        public <T> T executeWithIdempotency(String idempotencyKey, Supplier<T> operation, Class<T> resultType) {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                logger.warn("Idempotency key is null or blank, executing without idempotency guarantee");
                return operation.get();
            }

            logger.debug("Checking idempotency for key: {}", idempotencyKey);

            var existingRequest = persistencePort.findByIdempotencyKey(idempotencyKey);
            if (existingRequest.isPresent()) {
                var request = existingRequest.get();
                logger.info("Idempotent request found for key: {}, status: {}", 
                        idempotencyKey, request.status());
                
                if (request.status() == com.investment.portfolio.domain.model.InvestorRequest.RequestStatus.COMPLETED) {
                    return resultType.cast(createSuccessResult(request));
                } else if (request.status() == com.investment.portfolio.domain.model.InvestorRequest.RequestStatus.FAILED) {
                    throw new IdempotencyException("Request already failed: " + request.failureReason(), request);
                }
            }

            try {
                T result = operation.get();
                logger.info("Idempotent operation completed for key: {}", idempotencyKey);
                return result;
            } catch (Exception e) {
                logger.error("Idempotent operation failed for key: {}", idempotencyKey, e);
                throw e;
            }
        }

        public boolean isDuplicate(String idempotencyKey) {
            if (idempotencyKey == null || idempotencyKey.isBlank()) {
                return false;
            }
            return persistencePort.findByIdempotencyKey(idempotencyKey).isPresent();
        }

        public void recordDuplicate(String idempotencyKey) {
            idempotencyStore.put(idempotencyKey, new IdempotencyRecord(idempotencyKey, System.currentTimeMillis()));
            logger.debug("Recorded duplicate check for key: {}", idempotencyKey);
        }

        private Object createSuccessResult(com.investment.portfolio.domain.model.InvestorRequest request) {
            return request;
        }

        private record IdempotencyRecord(String key, long timestamp) {}

        public static class IdempotencyException extends RuntimeException {
            private final com.investment.portfolio.domain.model.InvestorRequest existingRequest;

            public IdempotencyException(String message, com.investment.portfolio.domain.model.InvestorRequest existingRequest) {
                super(message);
                this.existingRequest = existingRequest;
            }

            public com.investment.portfolio.domain.model.InvestorRequest getExistingRequest() {
                return existingRequest;
            }
        }
    }
}