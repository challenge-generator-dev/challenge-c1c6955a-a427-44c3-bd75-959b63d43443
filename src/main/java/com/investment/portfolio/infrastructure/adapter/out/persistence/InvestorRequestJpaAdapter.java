package com.investment.portfolio.infrastructure.adapter.out.persistence;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.model.InvestorRequest.RequestStatus;
import com.investment.portfolio.domain.port.out.InvestorRequestPersistencePort;
import com.investment.portfolio.infrastructure.adapter.out.persistence.entity.InvestorRequestEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de persistencia que implementa el puerto de salida utilizando
 * Spring Data JPA y JPA Repository para interactuar con la base de datos.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InvestorRequestJpaAdapter implements InvestorRequestPersistencePort {

    private final InvestorRequestRepository repository;
    private final InvestorRequestMapper mapper;

    @Override
    @Transactional
    public InvestorRequest save(@NotNull @Valid InvestorRequest request) {
        log.debug("Guardando solicitud de inversor: idempotencyKey={}", request.idempotencyKey());
        InvestorRequestEntity entity = mapper.toEntity(request);
        InvestorRequestEntity savedEntity = repository.save(entity);
        log.info("Solicitud guardada con id: {}", savedEntity.getId());
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorRequest> findById(@NotNull UUID requestId) {
        log.debug("Buscando solicitud por id: {}", requestId);
        return repository.findById(requestId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorRequest> findByIdempotencyKey(@NotNull String idempotencyKey) {
        log.debug("Buscando solicitud por clave de idempotencia: {}", idempotencyKey);
        return repository.findByIdempotencyKey(idempotencyKey)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void updateStatus(@NotNull UUID requestId, @NotNull RequestStatus status) {
        log.debug("Actualizando estado de solicitud {} a {}", requestId, status);
        InvestorRequestEntity entity = repository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Solicitud no encontrada con id: " + requestId));
        entity.setStatus(mapper.toEntityStatus(status));
        repository.save(entity);
        log.info("Estado actualizado exitosamente para solicitud: {}", requestId);
    }

    @Override
    @Transactional
    public void updateFailureReason(@NotNull UUID requestId, @NotNull String failureReason) {
        log.debug("Actualizando razón de fallo para solicitud: {}", requestId);
        InvestorRequestEntity entity = repository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Solicitud no encontrada con id: " + requestId));
        entity.setFailureReason(failureReason);
        entity.setStatus(mapper.toEntityStatus(RequestStatus.FAILED));
        repository.save(entity);
        log.info("Razón de fallo actualizada para solicitud: {}", requestId);
    }
}