package com.tbs.account.service.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record DebitCompleted(
        UUID transferId,
        String senderAgency,
        String senderAccountNumber,
        BigDecimal amount
) {
}
