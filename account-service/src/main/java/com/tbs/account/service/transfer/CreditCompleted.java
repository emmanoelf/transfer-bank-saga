package com.tbs.account.service.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditCompleted(
        UUID transferId,
        String receiverAgency,
        String receiverAccountNumber,
        BigDecimal amount
) {
}
