package com.tbs.account.service.repository;

import com.tbs.account.service.account.Account;
import java.util.UUID;

public interface AccountRepository {

    Account findById(UUID id);
    Account findByAgencyAndAccountNumber(
            String agency,
            String accountNumber
    );
    Account save(Account account);

}
