package com.tbs.fraud.service.service;

import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import com.tbs.fraud.service.fraud.FraudAnalysisResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudService {
    private static final BigDecimal LIMIT = new BigDecimal("5000");

    public FraudAnalysisResponse analyze(FraudAnalysisRequest fraudAnalysisRequest){
        boolean isApproved = fraudAnalysisRequest.amount().compareTo(LIMIT) <= 0;

        return new FraudAnalysisResponse(isApproved);
    }
}
