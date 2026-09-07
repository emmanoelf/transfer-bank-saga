package com.tbs.transfer.service.persistence.mapper;

import com.tbs.transfer.service.persistence.entity.TransferEntity;
import com.tbs.transfer.service.transfer.Transfer;

public class TransferMapper {
    public static Transfer toDomain(TransferEntity entity) {
        return new Transfer(
                entity.getId(),
                entity.getSenderAgency(),
                entity.getSenderAccountNumber(),
                entity.getReceiverAgency(),
                entity.getReceiverAccountNumber(),
                entity.getAmount(),
                entity.getStatus()
        );
    }

    public static TransferEntity toEntity(Transfer transfer) {
        return TransferEntity.of(
                transfer.getId(),
                transfer.getSenderAgency(),
                transfer.getSenderAccountNumber(),
                transfer.getReceiverAgency(),
                transfer.getReceiverAccountNumber(),
                transfer.getAmount(),
                transfer.getStatus()
        );
    }
}
