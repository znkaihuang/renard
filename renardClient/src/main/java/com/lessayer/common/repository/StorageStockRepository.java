package com.lessayer.common.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lessayer.common.entity.StorageStock;

@Repository
@Transactional
public class StorageStockRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private TypedQuery<StorageStock> query;
	
	// PSQL Name: "find_all"
	// PSQL Query: "select s from StorageStock s"
	public List<StorageStock> retrieveAllStocks() {
		
		query = entityManager.createNamedQuery("find_all_stocks", StorageStock.class);
		return query.getResultList();
		
	}
	
	// PSQL Name: "find_by_stockId"
	// PSQL Query: "select s from StorageStock s where s.stockId LIKE :stockId"
	public StorageStock retrieveStockByStockId(String stockId) {
		
	    query = entityManager.createNamedQuery("find_stock_by_stockId", StorageStock.class)
	            .setParameter("stockId", stockId);
	    StorageStock s;
	    try {
	    	
	    	s = query.getSingleResult();
	    	
	    }
	    catch (NoResultException e) {
			// TODO: handle exception
	    	return null;
		}
	    
		return s;
	    
	}
	
	public void createStock(StorageStock stock) {
		
		entityManager.merge(stock);
		
	}
	
	public void deleteStock(String stockId) {
		
		entityManager.remove(retrieveStockByStockId(stockId));
		
	}
	
	public void updateStock(String stockId, Double price, Integer volume) {
		
		StorageStock s = retrieveStockByStockId(stockId);
		s.setAvgPrice(price);
		s.setVolume(volume);
		entityManager.merge(s);
		
	}
	
}
