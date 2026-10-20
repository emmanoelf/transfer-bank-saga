package com.tbs.transfer.service.persistence.repository;

import com.tbs.transfer.service.persistence.entity.TransferEntity;
import com.tbs.transfer.service.persistence.mapper.TransferMapper;
import com.tbs.transfer.service.repository.TransferRepository;
import com.tbs.transfer.service.transfer.Transfer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransferRepositoryImpl implements TransferRepository {
    private final TransferJpaRepository transferJpaRepository;

    @Override
    public Optional<Transfer> findById(UUID id) {
        return this.transferJpaRepository
                .findById(id)
                .map(TransferMapper::toDomain);
    }

    @Override
    @Transactional
    public Transfer save(Transfer transfer) {
        TransferEntity entity = this.transferJpaRepository
                .findById(transfer.getId())
                .map(existingEntity -> {
                    TransferMapper.updateEntity(transfer, existingEntity);
                    return existingEntity;
                })
                .orElseGet(() -> TransferMapper.toEntity(transfer));

        TransferEntity savedEntity = this.transferJpaRepository.save(entity);

        return TransferMapper.toDomain(savedEntity);
    }
}
