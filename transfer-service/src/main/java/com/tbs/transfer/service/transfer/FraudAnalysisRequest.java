package com.tbs.transfer.service.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record FraudAnalysisRequest(
        UUID transferId,
        BigDecimal amount
) {
}
