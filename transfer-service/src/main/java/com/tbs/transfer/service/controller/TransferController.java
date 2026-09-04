package com.tbs.transfer.service.controller;

import com.tbs.transfer.service.service.TransferService;
import com.tbs.transfer.service.transfer.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TransferRequest transferRequest){
        this.transferService.create(transferRequest);
        return ResponseEntity.accepted().build();
    }
}
