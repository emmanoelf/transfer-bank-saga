package com.tbs.transfer.service.transfer;

import java.util.UUID;

public record FraudAnalysisResult(
        UUID transferId,
        FraudDecision decision
) {
}
