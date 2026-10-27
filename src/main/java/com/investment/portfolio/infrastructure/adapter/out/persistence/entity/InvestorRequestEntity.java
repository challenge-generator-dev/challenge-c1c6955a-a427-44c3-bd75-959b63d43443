package com.investment.portfolio.infrastructure.adapter.out.persistence.entity;

import com.investment.portfolio.domain.model.InvestorRequest;
import com.investment.portfolio.domain.model.InvestorRequest.OperationType;
import com.investment.portfolio.domain.model.InvestorRequest.RequestStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "investor_requests", indexes = {
    @Index(name = "idx_idempotency_key", columnList = "idempotency_key", unique = true),
    @Index(name = "idx_request_status", columnList = "status"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestorRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 255)
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false, length = 50)
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private RequestStatus status;

    @Column(name = "failure_reason", length = 1000)
    private String failureReason;

    @Column(name = "investor_id", nullable = false, length = 100)
    private String investorId;

    @Column(name = "portfolio_id", length = 100)
    private String portfolioId;

    @Column(name = "amount", precision = 19, scale = 4)
    private java.math.BigDecimal amount;

    @Column(name = "asset_id", length = 100)
    private String assetId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Version
    @Column(name = "version")
    private Long version;

    public static InvestorRequestEntity fromDomain(InvestorRequest request) {
        if (request == null) {
            return null;
        }
        return InvestorRequestEntity.builder()
                .id(request.id())
                .idempotencyKey(request.idempotencyKey())
                .operationType(request.operationType())
                .status(request.status())
                .failureReason(request.failureReason())
                .investorId(request.investorId())
                .portfolioId(request.portfolioId())
                .amount(request.amount())
                .assetId(request.assetId())
                .createdAt(request.createdAt())
                .updatedAt(request.updatedAt())
                .processedAt(request.processedAt())
                .build();
    }

    public InvestorRequest toDomain() {
        return new InvestorRequest(
                this.id,
                this.idempotencyKey,
                this.operationType,
                this.status,
                this.failureReason,
                this.investorId,
                this.portfolioId,
                this.amount,
                this.assetId,
                this.createdAt,
                this.updatedAt,
                this.processedAt
        );
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}