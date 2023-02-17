package com.bankly.transactionservice.Repository;

import com.bankly.transactionservice.Entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {
}
