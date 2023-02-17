package com.bankly.transactionservice.DTO;

import com.bankly.transactionservice.VO.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class TransactionDTO {
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
