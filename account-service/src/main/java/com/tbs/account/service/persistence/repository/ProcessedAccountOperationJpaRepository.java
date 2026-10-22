package com.tbs.account.service.persistence.repository;

import com.tbs.account.service.account.AccountOperationType;
import com.tbs.account.service.persistence.entity.ProcessedAccountOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ProcessedAccountOperationJpaRepository extends JpaRepository<ProcessedAccountOperationEntity, UUID> {
    @Modifying
    @Query(value = """
            INSERT INTO processed_account_operations (
                id,
                transfer_id,
                operation_type,
                agency,
                account_number,
                amount
            )
            VALUES (
                :id,
                :transferId,
                :operationType,
                :agency,
                :accountNumber,
                :amount
            )
            ON CONFLICT (transfer_id, operation_type) DO NOTHING
            """, nativeQuery = true)
    int insertIfAbsent(
            @Param("id") UUID id,
            @Param("transferId") UUID transferId,
            @Param("operationType") String operationType,
            @Param("agency") String agency,
            @Param("accountNumber") String accountNumber,
            @Param("amount") BigDecimal amount
    );
    Optional<ProcessedAccountOperationEntity>
    findByTransferIdAndOperationType(
            UUID transferId,
            AccountOperationType operationType
    );
}
