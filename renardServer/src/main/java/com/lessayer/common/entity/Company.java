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
public class Company {
	
	@JsonAlias("出表日期")
	private String requestInfoDate;
	@JsonAlias("公司代號")
	private String companyId;
	@JsonAlias("公司名稱")
	private String companyName; 
	@JsonAlias("公司簡稱")
	private String companyAbbrev; 
	@JsonAlias("外國企業註冊地國")
	private String foreignEntrepreneurRegisMap; 
	@JsonAlias("產業別")
	private String industryType;
	@JsonAlias("住址")
	private String address;
	@JsonAlias("營利事業統一編號")
	private String unifiedBusinessNum;
	@JsonAlias("董事長")
	private String chairman;
	@JsonAlias("總經理")
	private String generalManager;
	@JsonAlias("發言人")
	private String spokesman;
	@JsonAlias("發言人職稱")
	private String spokesmanTitle;
	@JsonAlias("代理發言人")
	private String deputySpokesman;
	@JsonAlias("總機電話")
	private String telephone;
	@JsonAlias("成立日期")
	private String incorporationDate;
	@JsonAlias("上市日期")
	private String listingDate; 
	@JsonAlias("普通股每股面額")
	private String commonStockParValue;
	@JsonAlias("實收資本額")
	private String capital;
	@JsonAlias("私募股數")
	private String privateStockShares;
	@JsonAlias("特別股")
	private String preferredStockShares;
	@JsonAlias("編制財務報表類型")
	private String financialReportType;
	@JsonAlias("股票過戶機構")
	private String stockTransferAgent;
	@JsonAlias("過戶電話")
	private String stockTransferAgentTelephone;
	@JsonAlias("過戶地址")
	private String stockTransferAgentAddress;
	@JsonAlias("簽證會計師事務所")
	private String accountingFirm;
	@JsonAlias("簽證會計師1")
	private String accountant1;
	@JsonAlias("簽證會計師2")
	private String accountant2;
	@JsonAlias("英文簡稱")
	private String symbol;
	@JsonAlias("英文通訊地址")
	private String englishAddress;
	@JsonAlias("傳真機號碼")
	private String fax;
	@JsonAlias("電子郵件信箱")
	private String email;
	@JsonAlias("網址")
	private String website;
	
}
