package com.lessayer.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lessayer.common.entity.StorageStock;
import com.lessayer.common.entity.Transaction;
import com.lessayer.common.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository repository;
	@Autowired
	StorageStockService stockService;
	
	public void createTransaction(String date, String stockId, Double price, Integer volume, 
			String buyOrSell) {
		
		if(buyOrSell.compareTo("買進") == 0) {
			
			repository.createTransaction(new Transaction(date, stockId, price, volume, "買進", 0.0));
			stockService.buyStock(stockId, price, volume);
		
		}
		else {
			
			StorageStock soldStock = stockService.returnStorageStockByStockId(stockId);
			if(Optional.ofNullable(soldStock).isPresent()) {
				
				Double sellProfit = price * volume - soldStock.avgPrice * volume;
				repository.createTransaction(
						new Transaction(date, stockId, price, volume, "賣出", sellProfit));
				stockService.sellStock(stockId, price, volume);
			
			}
			else {
				
				return;
				
			}
			
		}
	}
	
	public List<Transaction> returnAllTransactions() {
		
		return repository.retrieveAllTransactions();
		
	}
	
	public Transaction returnLatestTransaction() {
		
		return repository.retrieveLatestTransaction();
		
	}
	
	public Double calculateTotalSellProfit() {
		
		return repository.calculateTotalSellProfit();
		
	}
	
}
