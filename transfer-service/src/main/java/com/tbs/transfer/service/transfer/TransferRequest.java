package com.tbs.transfer.service.transfer;

import java.math.BigDecimal;

public record TransferRequest(
        String senderAgency,
        String senderAccountNumber,
        String receiverAgency,
        String receiverAccountNumber,
        BigDecimal amount
) {
}
