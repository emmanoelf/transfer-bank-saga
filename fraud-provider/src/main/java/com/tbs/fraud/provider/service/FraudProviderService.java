package com.tbs.fraud.provider.service;

import com.tbs.fraud.provider.fraud.FraudAnalysisRequest;
import com.tbs.fraud.provider.fraud.FraudAnalysisResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudProviderService {
    private static final BigDecimal LIMIT = new BigDecimal("5000");

    public FraudAnalysisResponse analyze(FraudAnalysisRequest fraudAnalysisRequest){
        if(fraudAnalysisRequest.amount().compareTo(new BigDecimal("8888")) == 0){
            this.simulateSlowResponse();
        }

        boolean isApproved = fraudAnalysisRequest.amount().compareTo(LIMIT) <= 0;
        return new FraudAnalysisResponse(isApproved);
    }

    private void simulateSlowResponse() {
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Fraud provider interrupted", e);
        }
    }
}
