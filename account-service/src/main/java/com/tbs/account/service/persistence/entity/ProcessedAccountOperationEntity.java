package com.tbs.account.service.persistence.entity;

import com.tbs.account.service.account.AccountOperationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "processed_account_operations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProcessedAccountOperationEntity {
    @Id
    private UUID id;

    @Column(name = "transfer_id", nullable = false)
    private UUID transferId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "operation_type", nullable = false, length = 10)
    private AccountOperationType operationType;

    @Column(nullable = false, length = 10)
    private String agency;

    @Column(name = "account_number", nullable = false, length = 30)
    private String accountNumber;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "processed_at", nullable = false, updatable = false)
    private LocalDateTime processedAt;
}
