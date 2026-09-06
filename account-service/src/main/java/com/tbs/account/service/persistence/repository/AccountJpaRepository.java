package com.tbs.account.service.persistence.repository;

import com.tbs.account.service.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByAgencyAndAccountNumber(
            String agency,
            String accountNumber
    );
}
