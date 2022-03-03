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
public class InstantStockInfo {
	
	@JsonAlias("a")
	private String sellPrice;
	@JsonAlias("b")
	private String purchasePrice;
	@JsonAlias("bp")
	private String bp;
	@JsonAlias("c")
	private String companyId;
	@JsonAlias("ch")
	private String ch;
	@JsonAlias("d")
	private String date;
	@JsonAlias("ex")
	private String listedType;
	@JsonAlias("f")
	private String sellVolume;
	@JsonAlias("g")
	private String purchaseVolume;
	@JsonAlias("h")
	private String highestPrice;
	@JsonAlias("i")
	private String i;
	@JsonAlias("ip")
	private String ip;
	@JsonAlias("it")
	private String it;
	@JsonAlias("l")
	private String lowestPrice;
	@JsonAlias("mt")
	private String mt;
	@JsonAlias("n")
	private String abbrevCompanyName;
	@JsonAlias("nf")
	private String companyFullName;
	@JsonAlias("o")
	private String openingPrice;
	@JsonAlias("p")
	private String p;
	@JsonAlias("ps")
	private String ps;
	@JsonAlias("pz")
	private String pz;
	@JsonAlias("s")
	private String s;
	@JsonAlias("t")
	private String time;
	@JsonAlias("tlong")
	private String epochMilliSecond;
	@JsonAlias("ts")
	private String ts;
	@JsonAlias("tv")
	private String transactionVolume;
	@JsonAlias("u")
	private String limitUpPrice;
	@JsonAlias("v")
	private String cumulativeVolume;
	@JsonAlias("w")
	private String limitDownPrice;
	@JsonAlias("y")
	private String yesterdayClosingPrice;
	@JsonAlias("z")
	private String currentPrice;
	
}
