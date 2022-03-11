package com.lessayer.common.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

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
public class Stock {
	
	@JsonAlias("Code")
	public String code;
	@JsonAlias("Name")
	public String name;
	@JsonAlias("日期")
	public String date;
	@JsonAlias({"成交股數", "TradeVolume"})
	public String tradeVolume;
	@JsonAlias({"成交金額", "TradeValue"})
	public String tradeValue;
	@JsonAlias({"開盤價", "OpeningPrice"})
	public String openingPrice;
	@JsonAlias({"最高價", "HighestPrice"})
	public String highestPrice;
	@JsonAlias({"最低價", "LowestPrice"})
	public String lowestPrice;
	@JsonAlias({"收盤價", "ClosingPrice"})
	public String closingPrice;
	@JsonAlias({"漲跌價差", "Change"})
	public String change;
	@JsonAlias({"成交筆數", "Transaction"})
	public String transaction;
	
}
