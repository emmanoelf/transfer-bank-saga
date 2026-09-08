package com.tbs.fraud.service.fraud;

import java.util.UUID;

public record FraudAnalysisResult(
        UUID transferId,
        FraudDecision decision
) {
}
