package com.tbs.account.service.consumer;

import com.tbs.account.service.service.AccountService;
import com.tbs.account.service.transfer.TransferCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEventConsumer {
    private final AccountService accountService;

    @KafkaListener(topics = "transfer.created")
    public void consume(TransferCreated event){
        this.accountService.debit(event.senderAgency(), event.senderAccountNumber(), event.amount());
        System.out.println("Débito realizado para a conta: " + event.senderAccountNumber());
    }
}
