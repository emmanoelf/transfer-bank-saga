package com.tbs.transfer.service.service;

import com.tbs.transfer.service.producer.TransferEventProducer;
import com.tbs.transfer.service.transfer.Transfer;
import com.tbs.transfer.service.transfer.TransferCreated;
import com.tbs.transfer.service.transfer.TransferRequest;
import com.tbs.transfer.service.transfer.TransferStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final TransferEventProducer producer;

    public void create(TransferRequest request){
        Transfer transfer = this.toTransfer(request);

        TransferCreated event = this.toTransferCreated(transfer);

        this.producer.publish(event);
    }

    private Transfer toTransfer(TransferRequest request){
        return new Transfer(
                UUID.randomUUID(),
                request.senderAccountId(),
                request.receiverAccountId(),
                request.amount(),
                TransferStatus.PENDING
        );
    }

    private TransferCreated toTransferCreated(Transfer transfer){
        return new TransferCreated(
                transfer.getId(),
                transfer.getSenderAccount(),
                transfer.getReceiverAccount(),
                transfer.getAmount()
        );
    }
}
