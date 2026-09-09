package com.tbs.transfer.service.kafka;

import com.tbs.transfer.service.transfer.FraudAnalysisRequest;
import com.tbs.transfer.service.transfer.TransferCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferEventProducer {
    private static final String TRANSFER_CREATED_TOPIC = "transfer.created";
    private static final String FRAUD_ANALYSIS_REQUESTED_TOPIC = "fraud.analysis.requested";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(TransferCreated event){
        this.kafkaTemplate.send(TRANSFER_CREATED_TOPIC, event);
    }

    public void publish(FraudAnalysisRequest event){
        this.kafkaTemplate.send(FRAUD_ANALYSIS_REQUESTED_TOPIC, event);
    }
}
