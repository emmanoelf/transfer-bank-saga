package com.tbs.fraud.service.external;

import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "fraud-provider", url = "${fraud-provider.url}")
public interface FraudProviderClient {

    @PostMapping("/fraud/analyze")
    FraudProviderResponse analyze(@RequestBody FraudAnalysisRequest fraudAnalysisRequest);
}
