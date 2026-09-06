package com.tbs.account.service.consumer;

import com.tbs.account.service.transfer.TransferCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AccountEventConsumer {

    @KafkaListener(topics = "transfer.created")
    public void consume(TransferCreated event){
        System.out.println("Account Service recebeu TransferCreated: " + event);
    }
}
