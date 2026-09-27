package com.tbs.fraud.service.kafka;

import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import com.tbs.fraud.service.fraud.FraudAnalysisResponse;
import com.tbs.fraud.service.fraud.FraudAnalysisResult;
import com.tbs.fraud.service.service.FraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudEventConsumer {
    private final FraudService fraudService;
    private final FraudEventProducer fraudEventProducer;

    @KafkaListener(topics = "fraud.analysis.requested")
    public void consume(FraudAnalysisRequest fraudAnalysisRequest){
        FraudAnalysisResponse response = this.fraudService.analyze(fraudAnalysisRequest);

        FraudAnalysisResult result = new FraudAnalysisResult(
                fraudAnalysisRequest.transferId(),
                response.decision()
        );

        this.fraudEventProducer.publish(result);
        System.out.println("FRAUD ANALYSIS RESULT PUBLICADO: " + result);
    }
}
