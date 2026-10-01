package com.tbs.account.service.transfer;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditRequested(
        UUID transferId,
        String receiverAgency,
        String receiverAccountNumber,
        BigDecimal amount
) {
}
