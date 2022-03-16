package com.lessayer.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lessayer.common.entity.Transaction;
import com.lessayer.common.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository repository;
	
	public void createTransaction(String date, String stockId, Double price, Integer volume, 
			String buyOrSell) {
		
		repository.createTransaction(new Transaction(date, stockId, price, volume, buyOrSell));
		
	}
	
	public List<Transaction> returnAllTransactions() {
		
		return repository.retrieveAllTransactions();
		
	}
	
}
