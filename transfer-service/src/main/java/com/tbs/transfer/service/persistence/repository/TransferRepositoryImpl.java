package com.tbs.transfer.service.persistence.repository;

import com.tbs.transfer.service.persistence.entity.TransferEntity;
import com.tbs.transfer.service.persistence.mapper.TransferMapper;
import com.tbs.transfer.service.repository.TransferRepository;
import com.tbs.transfer.service.transfer.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryImpl implements TransferRepository {
    private final TransferJpaRepository transferJpaRepository;

    @Override
    public Transfer findById(UUID id) {
        return this.transferJpaRepository
                .findById(id)
                .map(TransferMapper::toDomain)
                .orElse(null);
    }

    @Override
    public Transfer save(Transfer transfer) {
        TransferEntity entity = TransferMapper.toEntity(transfer);
        TransferEntity savedEntity = this.transferJpaRepository.save(entity);

        return TransferMapper.toDomain(savedEntity);
    }
}
