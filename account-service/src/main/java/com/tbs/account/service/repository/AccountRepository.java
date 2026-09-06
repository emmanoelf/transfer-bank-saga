package com.tbs.account.service.repository;

import com.tbs.account.service.account.Account;
import com.tbs.account.service.account.AccountStatus;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class AccountRepository {

    private final Map<UUID, Account> accounts = new HashMap<>();

    public AccountRepository() {
        Account account1 = new Account(
                UUID.randomUUID(),
                "0001",
                "123456-7",
                new BigDecimal("5000.00"),
                AccountStatus.ACTIVE
        );

        Account account2 = new Account(
                UUID.randomUUID(),
                "0001",
                "987654-3",
                new BigDecimal("1000.00"),
                AccountStatus.ACTIVE
        );

        accounts.put(account1.getId(), account1);
        accounts.put(account2.getId(), account2);

        System.out.println("Account 1 ID: " + account1.getId());
        System.out.println("Account 2 ID: " + account2.getId());
    }

    public Account findById(UUID id) {
        return accounts.get(id);
    }
}
