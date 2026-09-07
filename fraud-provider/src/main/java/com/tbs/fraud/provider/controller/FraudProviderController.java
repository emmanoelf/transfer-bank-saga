package com.tbs.fraud.provider.controller;

import com.tbs.fraud.provider.fraud.FraudAnalysisRequest;
import com.tbs.fraud.provider.fraud.FraudAnalysisResponse;
import com.tbs.fraud.provider.service.FraudProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraud")
@RequiredArgsConstructor
public class FraudProviderController {
    private final FraudProviderService fraudProviderService;

    @PostMapping("/analyze")
    public FraudAnalysisResponse analyze(@RequestBody FraudAnalysisRequest request){
        return this.fraudProviderService.analyze(request);
    }
}
