package com.tbs.transfer.service.transfer;

import java.math.BigDecimal;

public record TransferRequest(
        String senderAccountId,
        String receiverAccountId,
        BigDecimal amount
) {
}
