package com.bankly.transactionservice.Entity;


import com.bankly.transactionservice.VO.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private Double amount;
    private String userId1;
    private String userId2;
    private String walletRIB1;
    private String walletRIB2;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDate dateOfExcecution;
}
