package com.tbs.fraud.service.service;

import com.tbs.fraud.service.external.FraudProviderClient;
import com.tbs.fraud.service.external.FraudProviderResponse;
import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import com.tbs.fraud.service.fraud.FraudAnalysisResponse;
import com.tbs.fraud.service.fraud.FraudDecision;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final FraudProviderClient fraudProviderClient;

    @Retry(name = "fraudProvider")
    @CircuitBreaker(name = "fraudProvider", fallbackMethod = "fraudProviderFallback")
    public FraudAnalysisResponse analyze(FraudAnalysisRequest fraudAnalysisRequest) {

        FraudProviderResponse response =
                fraudProviderClient.analyze(fraudAnalysisRequest);

        FraudDecision decision = response.isApproved()
                ? FraudDecision.APPROVED
                : FraudDecision.REJECTED;

        return new FraudAnalysisResponse(decision);
    }

    private FraudAnalysisResponse fraudProviderFallback(FraudAnalysisRequest fraudAnalysisRequest, Throwable throwable) {
        throw new IllegalStateException(
                "Fraud provider unavailable",
                throwable
        );
    }
}
