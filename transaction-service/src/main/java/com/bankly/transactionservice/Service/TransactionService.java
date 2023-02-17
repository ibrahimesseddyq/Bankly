package com.bankly.transactionservice.Service;


import com.bankly.transactionservice.Entity.AccountTransaction;
import com.bankly.transactionservice.Entity.Transaction;
import com.bankly.transactionservice.Proxy.WalletProxy;
import com.bankly.transactionservice.Repository.AccountTransactionRepository;
import com.bankly.transactionservice.Repository.TransactionRepository;
import com.bankly.transactionservice.VO.Status;
import com.bankly.transactionservice.VO.TransactionType;
import com.bankly.transactionservice.VO.WalletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    private WalletProxy walletProxy;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;
    public AccountTransaction saveDebitOrCredit(AccountTransaction accountTransaction){
        if(accountTransaction==null || accountTransaction.equals(new AccountTransaction())){
            throw new RuntimeException("accounttransaction is null or empty");
        }
        if(accountTransaction.getRib()==null || accountTransaction.getRib()==""){
            throw new RuntimeException("rib is empty");
        }
        if(accountTransaction.getAmount()<0){
            throw new RuntimeException("amount should be Positive");
        }
        if(accountTransaction.getTransactionType()==null){
            throw new RuntimeException("transactiontype should debit or credit");
        }
        accountTransactionRepository.save(accountTransaction);
        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setAmount(accountTransaction.getAmount());
        walletRequest.setRib(accountTransaction.getRib());
        if(accountTransaction.getTransactionType().equals(TransactionType.DEBIT)) {
            walletProxy.increaseAmount(walletRequest);
        }
        else if (accountTransaction.getTransactionType().equals(TransactionType.CREDIT)) {
            walletProxy.decreaseAmount(walletRequest);

        }
        return accountTransaction;
    }
    public Transaction saveTransaction(Transaction transaction){
        if(transaction == null){
            throw new RuntimeException("transaction should not be null");
        }
        if(transaction.getAmount() <=0 ){
            throw new RuntimeException("amount should be bigger than 0");
        }
        if(transaction.getUserId1().isEmpty() || transaction.getUserId2().isEmpty()){
            throw new RuntimeException("no user should be empty");
        }
        if (transaction.getWalletRIB1().isEmpty() || transaction.getWalletRIB2().isEmpty()){
            throw new RuntimeException("RIBs must not be empty");
        }
//        if(transaction.getDateOfExcecution().isAfter(LocalDate.now())){
//            throw new RuntimeException("date of excecution is not valid");
//        }
        Transaction transactionToBeSaved= transactionRepository.save(transaction);

        try {
            transaction.setStatus(Status.IN_PROGRESS);

            WalletRequest walletRequestIncreasing = new WalletRequest();
            walletRequestIncreasing.setAmount(transaction.getAmount());
            walletRequestIncreasing.setRib(transaction.getWalletRIB2());
            System.out.println(walletRequestIncreasing);
            ResponseEntity amountIncreasingResponse = walletProxy.increaseAmount(walletRequestIncreasing);
            System.out.println(walletRequestIncreasing);
            if (amountIncreasingResponse.getStatusCode() != HttpStatus.valueOf(200)) {
                throw new RuntimeException("the amount did not increased for the receiver");
            }
            WalletRequest walletRequestDecreasing =new WalletRequest();
            walletRequestDecreasing.setAmount(transaction.getAmount());
            walletRequestDecreasing.setRib(transaction.getWalletRIB1());
            System.out.println(walletRequestDecreasing);
            ResponseEntity amountDecreasingResponse =walletProxy.decreaseAmount(walletRequestDecreasing);
            if(amountDecreasingResponse.getStatusCode() != HttpStatus.valueOf(200)){
                throw new RuntimeException("the amount did not decreased from the sender account");
            }
        }catch (Exception e){
            transactionToBeSaved.setStatus(Status.CANCELED);
            throw new RuntimeException(e);
        }
        transactionToBeSaved.setStatus(Status.SUCCESS);

        return transactionToBeSaved;
    }
}
