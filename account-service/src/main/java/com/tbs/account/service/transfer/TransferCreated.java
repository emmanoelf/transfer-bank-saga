package com.tbs.account.service.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferCreated(
        UUID transferId,
        String senderAccountId,
        String receiverAccountId,
        BigDecimal amount
) {
}
