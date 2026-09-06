package com.tbs.transfer.service.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferCreated(
        UUID transferId,
        String senderAgency,
        String senderAccountNumber,
        String receiverAgency,
        String receiverAccountNumber,
        BigDecimal amount
        ) {
}
