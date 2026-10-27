package com.investment.portfolio.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InvestorRequest(
    UUID id,
    @NotNull(message = "El tipo de operación no puede ser nulo")
    OperationType operationType,
    @NotNull(message = "El inversor ID no puede ser nulo")
    UUID investorId,
    @Positive(message = "El monto debe ser positivo")
    BigDecimal amount,
    @NotNull(message = "La clave de idempotencia no puede ser nula")
    String idempotencyKey,
    LocalDateTime createdAt,
    RequestStatus status,
    String failureReason
) {
    public enum OperationType {
        PORTFOLIO_QUERY,
        INVESTMENT,
        WITHDRAWAL
    }

    public enum RequestStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    public InvestorRequest {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
    }

    public InvestorRequest withStatus(RequestStatus newStatus) {
        return new InvestorRequest(
            this.id,
            this.operationType,
            this.investorId,
            this.amount,
            this.idempotencyKey,
            this.createdAt,
            newStatus,
            this.failureReason
        );
    }

    public InvestorRequest withFailureReason(String reason) {
        return new InvestorRequest(
            this.id,
            this.operationType,
            this.investorId,
            this.amount,
            this.idempotencyKey,
            this.createdAt,
            RequestStatus.FAILED,
            reason
        );
    }
}