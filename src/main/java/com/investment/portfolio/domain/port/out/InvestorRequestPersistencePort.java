package com.investment.portfolio.domain.port.out;


import com.investment.portfolio.domain.model.RequestStatus;
import com.investment.portfolio.domain.model.InvestorRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface InvestorRequestPersistencePort {
    InvestorRequest save(@NotNull @Valid InvestorRequest request);

    Optional<InvestorRequest> findById(@NotNull UUID requestId);

    Optional<InvestorRequest> findByIdempotencyKey(@NotNull String idempotencyKey);

    void updateStatus(@NotNull UUID requestId, @NotNull InvestorRequest.RequestStatus status);

    void updateFailureReason(@NotNull UUID requestId, @NotNull String failureReason);
}