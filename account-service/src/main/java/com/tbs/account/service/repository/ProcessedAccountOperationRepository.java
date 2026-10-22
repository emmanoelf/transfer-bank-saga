package com.tbs.account.service.repository;

import com.tbs.account.service.account.AccountOperationType;
import com.tbs.account.service.account.ProcessedAccountOperation;

import java.util.Optional;
import java.util.UUID;

public interface ProcessedAccountOperationRepository {
    boolean registerIfAbsent(ProcessedAccountOperation processedAccountOperation);
    Optional<ProcessedAccountOperation> findByTransferIdAndOperationType(UUID transferId, AccountOperationType accountOperationType);
}
