package com.tbs.fraud.provider.fraud;

import java.math.BigDecimal;
import java.util.UUID;

public record FraudAnalysisRequest(
        UUID transferId,
        BigDecimal amount
) {
}
