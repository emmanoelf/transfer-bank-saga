package com.tbs.account.service.kafka;

import com.tbs.account.service.transfer.DebitCompleted;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventProducer {
    private static final String TOPIC = "debit.completed";
    private final KafkaTemplate<String, DebitCompleted> kafkaTemplate;

    public void publish(DebitCompleted event){
        this.kafkaTemplate.send(TOPIC, event);
    }
}
