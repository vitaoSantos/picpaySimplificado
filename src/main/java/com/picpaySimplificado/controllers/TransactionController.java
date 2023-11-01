package com.picpaySimplificado.controllers;

import com.picpaySimplificado.domain.transaction.Transaction;
import com.picpaySimplificado.dtos.TransactionDTO;
import com.picpaySimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO trasaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(trasaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
