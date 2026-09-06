package com.tbs.account.service.persistence.mapper;

import com.tbs.account.service.account.Account;
import com.tbs.account.service.persistence.entity.AccountEntity;

public class AccountMapper {

    public static Account toDomain(AccountEntity entity){
        return new Account(
                entity.getId(),
                entity.getAgency(),
                entity.getAccountNumber(),
                entity.getBalance(),
                entity.getStatus()
        );
    }

    public static AccountEntity toEntity(Account account) {
        return AccountEntity.of(
                account.getId(),
                account.getAgency(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getStatus()
        );
    }
}
