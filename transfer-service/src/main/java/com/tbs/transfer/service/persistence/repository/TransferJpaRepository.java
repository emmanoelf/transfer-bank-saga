package com.tbs.transfer.service.persistence.repository;

import com.tbs.transfer.service.persistence.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferJpaRepository extends JpaRepository<TransferEntity, UUID> {
}
