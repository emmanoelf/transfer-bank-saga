package com.tbs.fraud.service.service;

import com.tbs.fraud.service.external.FraudProviderClient;
import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import com.tbs.fraud.service.fraud.FraudAnalysisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudService {
    private final FraudProviderClient fraudProviderClient;

    public FraudAnalysisResponse analyze(FraudAnalysisRequest fraudAnalysisRequest){
        return fraudProviderClient.analyze(fraudAnalysisRequest);
    }
}
