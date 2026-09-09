package com.tbs.transfer.service.kafka;

import com.tbs.transfer.service.transfer.DebitCompleted;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransferEventConsumer {

    @KafkaListener(topics = "debit.completed")
    public void consume(DebitCompleted event){
        System.out.println("DEBIT COMPLETED RECEBIDO: " + event);
    }
}
