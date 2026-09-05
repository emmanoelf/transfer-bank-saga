package com.tbs.transfer.service.consumer;

import com.tbs.transfer.service.transfer.TransferCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransferEventConsumer {

    @KafkaListener(topics = "transfer.created")
    public void consume(TransferCreated event){
        System.out.println("TransferCreated recebido: " + event);
    }

}
