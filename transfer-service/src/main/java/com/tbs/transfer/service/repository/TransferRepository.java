package com.tbs.transfer.service.repository;

import com.tbs.transfer.service.transfer.Transfer;

import java.util.Optional;
import java.util.UUID;

public interface TransferRepository {
    Optional<Transfer> findById(UUID id);
    Transfer save(Transfer transfer);
}
