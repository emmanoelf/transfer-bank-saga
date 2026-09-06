package com.tbs.account.service.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class Account {
    private UUID id;
    private String agency;
    private String accountNumber;
    private BigDecimal balance;
    private AccountStatus status;

    public void debit(BigDecimal amount){
        this.validateAmount(amount);
        this.validateActive();
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount){
        this.validateAmount(amount);
        this.validateActive();
        this.balance = this.balance.add(amount);
    }

    private void validateAmount(BigDecimal amount){
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount below or equal 0");
        }
    }

    private void validateActive(){
        if(status != AccountStatus.ACTIVE){
            throw new IllegalArgumentException("Account is not active");
        }
    }

    public void close(){
        this.status = AccountStatus.CLOSED;
    }

    public void activate(){
        if(this.status == AccountStatus.CLOSED){
            throw new IllegalStateException("Account already closed");
        }
        this.status = AccountStatus.ACTIVE;
    }

    public void block(){
        if(this.status == AccountStatus.CLOSED){
            throw new IllegalStateException("Account already closed");
        }
        this.status = AccountStatus.BLOCKED;
    }
}
