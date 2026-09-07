package com.tbs.fraud.service.controller;

import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import com.tbs.fraud.service.fraud.FraudAnalysisResponse;
import com.tbs.fraud.service.service.FraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraud")
@RequiredArgsConstructor
public class FraudController {
    private final FraudService fraudService;

    @PostMapping("/analyze")
    public FraudAnalysisResponse analyze(@RequestBody FraudAnalysisRequest fraudAnalysisRequest){
        return this.fraudService.analyze(fraudAnalysisRequest);
    }
}
