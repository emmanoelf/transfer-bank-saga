package com.tbs.transfer.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    private UUID id;
    private String senderAgency;
    private String senderAccountNumber;
    private String receiverAgency;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private TransferStatus status;
}
