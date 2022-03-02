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
	
	@JsonAlias("日期")
	public String date;
	@JsonAlias("成交股數")
	public String tradeVolume;
	@JsonAlias("成交金額")
	public String tradeValue;
	@JsonAlias("開盤價")
	public String openingPrice;
	@JsonAlias("最高價")
	public String highestPrice;
	@JsonAlias("最低價")
	public String lowestPrice;
	@JsonAlias("收盤價")
	public String closingPrice;
	@JsonAlias("漲跌價差")
	public String change;
	@JsonAlias("成交筆數")
	public String transaction;
	
}
