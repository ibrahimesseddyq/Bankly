package com.bankly.transactionservice.Repository;

import com.bankly.transactionservice.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
