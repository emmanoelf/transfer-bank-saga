package com.tbs.transfer.service.persistence.entity;

import com.tbs.transfer.service.transfer.TransferStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "transfers")
public class TransferEntity {

    @Id
    private UUID id;
    private String senderAgency;
    private String senderAccountNumber;
    private String receiverAgency;
    private String receiverAccountNumber;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static TransferEntity of(
            UUID id,
            String senderAgency,
            String senderAccountNumber,
            String receiverAgency,
            String receiverAccountNumber,
            BigDecimal amount,
            TransferStatus status
    ) {
        TransferEntity entity = new TransferEntity();

        entity.id = id;
        entity.senderAgency = senderAgency;
        entity.senderAccountNumber = senderAccountNumber;
        entity.receiverAgency = receiverAgency;
        entity.receiverAccountNumber = receiverAccountNumber;
        entity.amount = amount;
        entity.status = status;

        return entity;
    }
}
