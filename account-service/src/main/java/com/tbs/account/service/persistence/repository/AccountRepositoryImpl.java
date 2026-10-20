package com.tbs.account.service.persistence.repository;

import com.tbs.account.service.account.Account;
import com.tbs.account.service.persistence.entity.AccountEntity;
import com.tbs.account.service.persistence.mapper.AccountMapper;
import com.tbs.account.service.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account findById(UUID id) {
        return this.accountJpaRepository
                .findById(id)
                .map(AccountMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Account findByAgencyAndAccountNumber(String agency, String accountNumber) {
        return this.accountJpaRepository
                .findByAgencyAndAccountNumber(agency, accountNumber)
                .map(AccountMapper::toDomain)
                .orElse(null);
    }

    @Override
    @Transactional
    public Account save(Account account) {
        AccountEntity entity = this.accountJpaRepository
                .findById(account.getId())
                .map(existingEntity -> {
                    AccountMapper.updateEntity(account, existingEntity);
                    return existingEntity;
                })
                .orElseGet(() -> AccountMapper.toEntity(account));

        AccountEntity savedEntity = this.accountJpaRepository.save(entity);

        return AccountMapper.toDomain(savedEntity);
    }
}
