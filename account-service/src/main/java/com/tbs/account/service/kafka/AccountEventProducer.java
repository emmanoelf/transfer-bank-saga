package com.tbs.account.service.kafka;

import com.tbs.account.service.transfer.CreditCompleted;
import com.tbs.account.service.transfer.DebitCompleted;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventProducer {
    private static final String DEBIT_COMPLETED_TOPIC = "debit.completed";
    private static final String CREDIT_COMPLETED_TOPIC = "credit.completed";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(DebitCompleted event){
        this.kafkaTemplate.send(DEBIT_COMPLETED_TOPIC, event);
    }

    public void publish(CreditCompleted event){
        this.kafkaTemplate.send(CREDIT_COMPLETED_TOPIC, event);
    }
}
