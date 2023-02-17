package com.bankly.transactionservice.Controller;

import com.bankly.transactionservice.DTO.TransactionDTO;
import com.bankly.transactionservice.Entity.Transaction;
import com.bankly.transactionservice.Repository.TransactionRepository;
import com.bankly.transactionservice.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction){

        return transactionService.saveTransaction(transaction);
    }
}
