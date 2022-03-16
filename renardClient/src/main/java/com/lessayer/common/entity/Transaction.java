package com.lessayer.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@NamedQuery(name = "find_all_transactions", 
			query = "select t from Transaction t")
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long transactionId;
	public String date;
	public String stockId;
	public Double price;
	public Integer volume;
	public String buyOrSell;
	
	public Transaction(String date, String stockId, Double price, Integer volume, String buyOrSell) {
		
		this.date = date;
		this.stockId = stockId;
		this.price = price;
		this.volume = volume;
		this.buyOrSell = buyOrSell;
		
	}
	
}
