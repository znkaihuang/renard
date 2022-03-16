package com.lessayer.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@NamedQuery(name = "find_all_stocks",
			query = "select s from StorageStock s")
@NamedQuery(name = "find_stock_by_stockId",
			query = "select s from StorageStock s where s.stockId LIKE :stockId")
@Entity
public class StorageStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long storageId;
	public String stockId;
	public Double avgPrice;
	public Integer volume;
	
	public StorageStock(String stockId, Double avgPrice, Integer volume) {
		
		this.stockId = stockId;
		this.avgPrice = avgPrice;
		this.volume = volume;
		
	}
	
	
}
