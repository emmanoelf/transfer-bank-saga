package com.tbs.transfer.service.controller;

import com.tbs.transfer.service.producer.TransferEventProducer;
import com.tbs.transfer.service.transfer.TransferRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    private final TransferEventProducer producer;

    public TransferController(TransferEventProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TransferRequest transferRequest){
        this.producer.publish(transferRequest);
        return ResponseEntity.accepted().build();
    }
}
