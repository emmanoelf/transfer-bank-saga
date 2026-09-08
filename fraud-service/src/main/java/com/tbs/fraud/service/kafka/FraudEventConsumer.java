package com.tbs.fraud.service.kafka;

import com.tbs.fraud.service.fraud.FraudAnalysisRequest;
import com.tbs.fraud.service.fraud.FraudAnalysisResponse;
import com.tbs.fraud.service.service.FraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudEventConsumer {
    private final FraudService fraudService;

    @KafkaListener(topics = "fraud.analysis.requested")
    public void consume(FraudAnalysisRequest fraudAnalysisRequest){
        System.out.println(
                "Mensagem recebida do Kafka: " + fraudAnalysisRequest
        );

        FraudAnalysisResponse response = fraudService.analyze(fraudAnalysisRequest);

        System.out.println(
                "Resultado da análise: " + response
        );
    }
}
