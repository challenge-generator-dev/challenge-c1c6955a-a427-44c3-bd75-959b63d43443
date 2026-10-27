package com.investment.portfolio.application.service;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.model.InvestorRequest.RequestStatus;
import com.investment.portfolio.domain.port.in.InvestorRequestPort;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Servicio de aplicación que implementa la lógica de negocio para enrutar y manejar
 * solicitudes de inversores, garantizando idempotencia y registrando trazas.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvestorRequestService implements InvestorRequestPort {

    private final InvestorRequestPersistencePort persistencePort;

    @Override
    @Transactional
    public InvestorRequest processInvestorRequest(@NotNull @Valid InvestorRequest request) {
        log.info("Iniciando procesamiento de solicitud de inversor: operationType={}, idempotencyKey={}",
                request.operationType(), request.idempotencyKey());

        Optional<InvestorRequest> existingRequest = persistencePort.findByIdempotencyKey(
                request.idempotencyKey());

        if (existingRequest.isPresent()) {
            InvestorRequest cached = existingRequest.get();
            log.info("Solicitud procesada previamente encontrada con id: {}", cached.id());
            return cached;
        }

        InvestorRequest savedRequest = persistencePort.save(request);
        log.info("Solicitud guardada exitosamente con id: {}", savedRequest.id());

        try {
            InvestorRequest processedRequest = executeOperation(savedRequest);
            persistencePort.updateStatus(processedRequest.id(), processedRequest.status());
            log.info("Operación completada exitosamente para solicitud: {}", processedRequest.id());
            return processedRequest;
        } catch (Exception e) {
            log.error("Error al procesar solicitud: {}", e.getMessage(), e);
            persistencePort.updateStatus(savedRequest.id(), RequestStatus.FAILED);
            persistencePort.updateFailureReason(savedRequest.id(), e.getMessage());
            throw e;
        }
    }

    @Override
    public InvestorRequest getInvestorRequestById(@NotNull UUID requestId) {
        log.debug("Consultando solicitud por id: {}", requestId);
        return persistencePort.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solicitud no encontrada con id: " + requestId));
    }

    @Override
    public InvestorRequest getInvestorRequestByIdempotencyKey(@NotNull String idempotencyKey) {
        log.debug("Consultando solicitud por clave de idempotencia: {}", idempotencyKey);
        return persistencePort.findByIdempotencyKey(idempotencyKey)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Solicitud no encontrada con clave de idempotencia: " + idempotencyKey));
    }

    private InvestorRequest executeOperation(InvestorRequest request) {
        log.info("Ejecutando operación: {}", request.operationType());

        return switch (request.operationType()) {
            case CONSULT_CARGO -> {
                log.info("Consultando cartera del inversor: {}", request.investorId());
                yield request.withStatus(RequestStatus.COMPLETED);
            }
            case INVEST -> {
                log.info("Procesando inversión para inversor: {}, monto: {}",
                        request.investorId(), request.amount());
                yield request.withStatus(RequestStatus.COMPLETED);
            }
            case WITHDRAW -> {
                log.info("Procesando retiro para inversor: {}, monto: {}",
                        request.investorId(), request.amount());
                yield request.withStatus(RequestStatus.COMPLETED);
            }
        };
    }
}