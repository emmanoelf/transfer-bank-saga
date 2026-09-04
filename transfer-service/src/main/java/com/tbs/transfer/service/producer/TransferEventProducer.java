package com.tbs.transfer.service.producer;

import com.tbs.transfer.service.transfer.TransferRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransferEventProducer {
    private static final String TOPIC = "transfer.created";
    private final KafkaTemplate<String, TransferRequest> kafkaTemplate;

    public TransferEventProducer(KafkaTemplate<String, TransferRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(TransferRequest request){
        this.kafkaTemplate.send(TOPIC, request);
    }
}
