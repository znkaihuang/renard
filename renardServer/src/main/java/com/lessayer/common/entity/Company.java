package com.lessayer.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company {
	
	private String requestInfoDate; // 出表日期
	private String companyId; // 公司代號
	private String companyName; // 公司名稱
	private String companyAbbrev; // 公司簡稱
	private String foreignEntrepreneurRegisMap; // 外國企業註冊地國
	private String industryType; // 產業別
	private String address; // 住址
	private String unifiedBusinessNum; // 營利事業統一編號
	private String chairman; // 董事長
	private String generalManager; // 總經理
	private String spokesman; // 發言人
	private String spokesmanTitle; // 發言人職稱
	private String deputySpokesman; // 代理發言人
	private String telephone; // 總機電話
	private String incorporationDate; // 成立日期
	private String listingDate; // 上市日期
	private String commonStockParValue; // 普通股每股面額
	private String capital; // 實收資本額
	private String privateStockShares; // 私募股數
	private String preferredStockShares; // 特別股
	private String financialReportType; // 編制財務報表類型
	private String stockTransferAgent; // 股票過戶機構
	private String stockTransferAgentTelephone; // 過戶電話
	private String stockTransferAgentAddress; // 過戶地址
	private String accountingFirm; // 簽證會計師事務所
	private String accountant1; // 簽證會計師1
	private String accountant2;   // 簽證會計師2
	private String symbol; // 英文簡稱
	private String englishAddress; // 英文通訊地址
	private String fax; // 傳真機號碼
	private String email; // 電子郵件信箱
	private String website; // 網址
	
}
