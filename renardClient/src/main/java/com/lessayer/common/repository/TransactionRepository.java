package com.lessayer.common.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lessayer.common.entity.Transaction;

@Repository
@Transactional
public class TransactionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	private TypedQuery<Transaction> query;
	private TypedQuery<Double> sumQuery;
	
	// PSQL Name: "find_all"
	// PSQL Query: "select t from Task t"
	public List<Transaction> retrieveAllTransactions() {
		
		query = entityManager.createNamedQuery("find_all_transactions", Transaction.class);
		return query.getResultList();
		
	}
	
	// PSQL Name: "find_latest_transaction",
	// PSQL Query: "select t from Transaction t where t.transactionId = "
	//             + "(select max(t.transactionId) from Transaction t)"
	public Transaction retrieveLatestTransaction() {
		
		query = entityManager.createNamedQuery("find_latest_transaction", Transaction.class);
		Transaction latestTransaction;
		try {
			
			latestTransaction = query.getSingleResult();
			
		}
		catch(NoResultException e) {
			
			return null;
			
		}
		return latestTransaction;
		
	}
	
	public Transaction createTransaction(Transaction transaction) {
		
		return entityManager.merge(transaction);
		
	}
	
	// PSQL Name: "calculate_sell_profit",
	// PSQL Query: "select sum(t.sellStockProfit) from Transaction t
	public Double calculateTotalSellProfit() {
		
		sumQuery = entityManager.createNamedQuery("calculate_sell_profit", Double.class);
		return sumQuery.getSingleResult();
		
	}
	
}
