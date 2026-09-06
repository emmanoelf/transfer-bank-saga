package com.tbs.account.service.service;

import com.tbs.account.service.account.Account;
import com.tbs.account.service.account.AccountStatus;
import com.tbs.account.service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account findById(UUID id){
        return this.accountRepository.findById(id);
    }

    public Account findByAgencyAndAccountNumber(String agency, String accountNumber){
        return this.accountRepository.findByAgencyAndAccountNumber(agency, accountNumber);
    }

    public void debit(String agency, String accountNumber, BigDecimal amount){
        Account account = this.findByAgencyAndAccountNumber(agency, accountNumber);

        if(account == null){
            throw new IllegalArgumentException("Account not found");
        }

        account.debit(amount);
    }

    public Account create(String agency, String accountNumber, BigDecimal initialBalance){
        Account account = new Account(UUID.randomUUID(), agency, accountNumber, initialBalance, AccountStatus.ACTIVE);
        return this.accountRepository.save(account);
    }
}
