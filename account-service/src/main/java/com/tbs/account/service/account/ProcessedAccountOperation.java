package com.tbs.account.service.account;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcessedAccountOperation(
        UUID transferId,
        AccountOperationType accountOperationType,
        String agency,
        String accountNumber,
        BigDecimal amount
) {
}
