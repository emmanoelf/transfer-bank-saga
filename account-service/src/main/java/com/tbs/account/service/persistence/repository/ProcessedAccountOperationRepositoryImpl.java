package com.tbs.account.service.persistence.repository;

import com.tbs.account.service.account.AccountOperationType;
import com.tbs.account.service.account.ProcessedAccountOperation;
import com.tbs.account.service.repository.ProcessedAccountOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProcessedAccountOperationRepositoryImpl implements ProcessedAccountOperationRepository {
    private final ProcessedAccountOperationJpaRepository processedAccountOperationJpaRepository;

    @Override
    public boolean registerIfAbsent(ProcessedAccountOperation processedAccountOperation) {
        int inserted = this.processedAccountOperationJpaRepository.insertIfAbsent(
                UUID.randomUUID(),
                processedAccountOperation.transferId(),
                processedAccountOperation.accountOperationType().name(),
                processedAccountOperation.agency(),
                processedAccountOperation.accountNumber(),
                processedAccountOperation.amount()
        );

        return inserted == 1;
    }

    @Override
    public Optional<ProcessedAccountOperation> findByTransferIdAndOperationType(
            UUID transferId,
            AccountOperationType operationType
    ) {
        return this.processedAccountOperationJpaRepository
                .findByTransferIdAndOperationType(transferId, operationType)
                .map(entity -> new ProcessedAccountOperation(
                        entity.getTransferId(),
                        entity.getOperationType(),
                        entity.getAgency(),
                        entity.getAccountNumber(),
                        entity.getAmount()
                ));
    }
}
