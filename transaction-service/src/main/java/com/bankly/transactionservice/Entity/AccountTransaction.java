package com.bankly.transactionservice.Entity;

import com.bankly.transactionservice.VO.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountTransaction {
    @Id
    @GeneratedValue
    private Long id;
    private String rib;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
