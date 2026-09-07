package com.tbs.fraud.provider.service;

import com.tbs.fraud.provider.fraud.FraudAnalysisRequest;
import com.tbs.fraud.provider.fraud.FraudAnalysisResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudProviderService {
    private static final BigDecimal LIMIT = new BigDecimal("5000");

    public FraudAnalysisResponse analyze(FraudAnalysisRequest fraudAnalysisRequest){
        boolean isApproved = fraudAnalysisRequest.amount().compareTo(LIMIT) <= 0;
        return new FraudAnalysisResponse(isApproved);
    }
}
