package com.tbs.fraud.service.kafka;

import com.tbs.fraud.service.fraud.FraudAnalysisResult;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudEventProducer {
    private static final String TOPIC = "fraud.analysis.result";
    private final KafkaTemplate<String, FraudAnalysisResult> kafkaTemplate;

    public void publish(FraudAnalysisResult event){
        this.kafkaTemplate.send(TOPIC, event);
    }
}
