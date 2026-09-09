package com.tbs.transfer.service.kafka;

import com.tbs.transfer.service.transfer.TransferCreated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransferEventProducer {
    private static final String TOPIC = "transfer.created";
    private final KafkaTemplate<String, TransferCreated> kafkaTemplate;

    public TransferEventProducer(KafkaTemplate<String, TransferCreated> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(TransferCreated event){
        this.kafkaTemplate.send(TOPIC, event);
    }
}
