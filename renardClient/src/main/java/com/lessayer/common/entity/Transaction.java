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
@NamedQuery(name = "find_latest_transaction",
			query = "select t from Transaction t where t.transactionId = "
					+ "(select max(t.transactionId) from Transaction t)")
@NamedQuery(name = "calculate_sell_profit",
			query = "select sum(t.sellStockProfit) from Transaction t")
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
	public Double sellStockProfit;
	
	public Transaction(String date, String stockId, Double price, Integer volume,
			String buyOrSell, Double sellStockProfit) {
		
		this.date = date;
		this.stockId = stockId;
		this.price = price;
		this.volume = volume;
		this.buyOrSell = buyOrSell;
		this.sellStockProfit = sellStockProfit;
		
	}
	
}
