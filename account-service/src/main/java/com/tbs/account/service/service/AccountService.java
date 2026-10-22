package com.tbs.account.service.service;

import com.tbs.account.service.account.Account;
import com.tbs.account.service.account.AccountOperationType;
import com.tbs.account.service.account.AccountStatus;
import com.tbs.account.service.account.ProcessedAccountOperation;
import com.tbs.account.service.repository.AccountRepository;
import com.tbs.account.service.repository.ProcessedAccountOperationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ProcessedAccountOperationRepository processedAccountOperationRepository;

    public Account findById(UUID id){
        return this.accountRepository.findById(id);
    }

    public Account findByAgencyAndAccountNumber(String agency, String accountNumber){
        return this.accountRepository.findByAgencyAndAccountNumber(agency, accountNumber);
    }

    @Transactional
    public void debit(UUID transferId, String agency, String accountNumber, BigDecimal amount){
        this.processOperation(new ProcessedAccountOperation(transferId, AccountOperationType.DEBIT, agency, accountNumber, amount));
    }

    @Transactional
    public void credit(UUID transferId, String agency, String accountNumber, BigDecimal amount){
        this.processOperation(new ProcessedAccountOperation(transferId, AccountOperationType.CREDIT, agency, accountNumber, amount));
    }

    private void processOperation(ProcessedAccountOperation processedOperation){
        if(processedOperation.amount() == null || processedOperation.amount().signum() <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        boolean registered = this.processedAccountOperationRepository.registerIfAbsent(processedOperation);
        if(!registered){
            ProcessedAccountOperation existing = this.processedAccountOperationRepository.findByTransferIdAndOperationType(
                    processedOperation.transferId(), processedOperation.accountOperationType()
            ).orElseThrow(() -> new IllegalStateException("Operation conflict, but no existing record was found"));

            this.validateDuplicate(existing, processedOperation);
            return ;
        }

        Account account = this.findByAgencyAndAccountNumber(processedOperation.agency(), processedOperation.accountNumber());
        if(account == null){
            throw new IllegalArgumentException("Account not found");
        }

        switch (processedOperation.accountOperationType()){
            case DEBIT -> account.debit(processedOperation.amount());
            case CREDIT -> account.credit(processedOperation.amount());
        }

        this.accountRepository.save(account);
    }

    private void validateDuplicate(
            ProcessedAccountOperation existing,
            ProcessedAccountOperation requested
    ) {
        boolean sameOperation =
                Objects.equals(existing.agency(), requested.agency())
                        && Objects.equals(existing.accountNumber(), requested.accountNumber())
                        && existing.amount().compareTo(requested.amount()) == 0;

        if (!sameOperation) {
            throw new IllegalStateException(
                    "The same transfer operation was received with different data: "
                            + requested.transferId()
                            + " / "
                            + requested.accountOperationType()
            );
        }
    }

    public Account create(String agency, String accountNumber, BigDecimal initialBalance){
        Account account = new Account(UUID.randomUUID(), agency, accountNumber, initialBalance, AccountStatus.ACTIVE);
        return this.accountRepository.save(account);
    }
}
