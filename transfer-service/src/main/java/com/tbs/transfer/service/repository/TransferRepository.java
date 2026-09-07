package com.tbs.transfer.service.repository;

import com.tbs.transfer.service.transfer.Transfer;

import java.util.UUID;

public interface TransferRepository {
    Transfer findById(UUID id);
    Transfer save(Transfer transfer);
}
