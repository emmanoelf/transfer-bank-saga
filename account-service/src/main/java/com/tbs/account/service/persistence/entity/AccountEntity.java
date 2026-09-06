package com.tbs.account.service.persistence.entity;

import com.tbs.account.service.account.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity {

    @Id
    private UUID id;
    private String agency;
    private String accountNumber;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AccountEntity of(
            UUID id,
            String agency,
            String accountNumber,
            BigDecimal balance,
            AccountStatus status
    ) {
        AccountEntity entity = new AccountEntity();

        entity.id = id;
        entity.agency = agency;
        entity.accountNumber = accountNumber;
        entity.balance = balance;
        entity.status = status;

        return entity;
    }
}
