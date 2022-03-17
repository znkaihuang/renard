package com.lessayer.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lessayer.common.entity.StorageStock;
import com.lessayer.common.repository.StorageStockRepository;

@Service
public class StorageStockService {
	
	@Autowired
	StorageStockRepository repository;
	
	public List<StorageStock> returnAllStorageStocks() {
		
		return repository.retrieveAllStocks();
		
	}
	
	public StorageStock returnStorageStockByStockId(String stockId) {
		
		return repository.retrieveStockByStockId(stockId);
		
	}
	
	public void buyStock(String stockId, Double price, Integer volume) {
		
		Optional<StorageStock> stockOptional = 
				Optional.ofNullable(repository.retrieveStockByStockId(stockId));
		if(stockOptional.isEmpty()) {
			
			repository.createStock(new StorageStock(stockId, price, volume));
			
		}
		else {
			
			StorageStock s = stockOptional.get();
			Double avgPrice = (s.getAvgPrice() * s.getVolume() + price * volume) /
					(s.getVolume() + volume);
			Integer totalVolume = s.getVolume() + volume;
			repository.updateStock(stockId, avgPrice, totalVolume);
			
		}
		
	}
	
	public void sellStock(String stockId, Double price, Integer volume) {
		
		Optional<StorageStock> stockOptional = 
				Optional.ofNullable(repository.retrieveStockByStockId(stockId));
		if(stockOptional.isEmpty()) {
			
			return;
			
		}
		else {
			
			StorageStock s = stockOptional.get();
			Integer totalVolume = s.getVolume() - volume;
			Double avgPrice = (s.getAvgPrice() * s.getVolume() - price * volume) / (totalVolume);
			if(totalVolume == 0) {
				
				repository.deleteStock(stockId);
			
			}
			else {
				
				repository.updateStock(stockId, avgPrice, totalVolume);
			
			}
			
		}
		
	}
	
}
