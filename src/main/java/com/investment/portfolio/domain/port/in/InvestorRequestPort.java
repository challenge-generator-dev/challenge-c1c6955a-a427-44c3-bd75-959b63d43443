package com.investment.portfolio.domain.port.in;

import com.investment.portfolio.domain.model.InvestorRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface InvestorRequestPort {
    InvestorRequest processInvestorRequest(@NotNull @Valid InvestorRequest request);

    InvestorRequest getInvestorRequestById(@NotNull UUID requestId);

    InvestorRequest getInvestorRequestByIdempotencyKey(@NotNull String idempotencyKey);
}