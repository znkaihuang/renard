package com.lessayer.common.repository;

import java.util.List;

import javax.persistence.EntityManager;
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
	
	// PSQL Name: "find_all"
	// PSQL Query: "select t from Task t"
	public List<Transaction> retrieveAllTransactions() {
		
		query = entityManager.createNamedQuery("find_all", Transaction.class);
		return query.getResultList();
		
	}
	
	public Transaction createTransaction(Transaction transaction) {
		
		return entityManager.merge(transaction);
		
	}
	
}
