package com.tbs.account.service.consumer;

import com.tbs.account.service.kafka.AccountEventProducer;
import com.tbs.account.service.service.AccountService;
import com.tbs.account.service.transfer.DebitCompleted;
import com.tbs.account.service.transfer.TransferCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventConsumer {
    private final AccountService accountService;
    private final AccountEventProducer accountEventProducer;

    @KafkaListener(topics = "transfer.created")
    public void consume(TransferCreated event){
        this.accountService.debit(event.senderAgency(), event.senderAccountNumber(), event.amount());
        DebitCompleted debitCompleted = new DebitCompleted(
                event.transferId(),
                event.senderAgency(),
                event.senderAccountNumber(),
                event.amount());

        this.accountEventProducer.publish(debitCompleted);
        System.out.println("DEBIT COMPLETED PUBLICADO: " + debitCompleted);
    }
}
