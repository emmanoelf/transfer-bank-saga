package com.tbs.account.service.service;

import com.tbs.account.service.account.Account;
import com.tbs.account.service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account findById(UUID id){
        return this.accountRepository.findById(id);
    }
}
