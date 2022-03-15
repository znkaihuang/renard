package com.lessayer.common.entity;

import java.util.HashMap;
import java.util.Map;

public class IndustryType {
	
	static private Map<String, String> IndustryTypeMap = new HashMap<String, String>();

	static public Map<String, String> returnIndustryTypMap()  {
		IndustryTypeMap.put("01", "水泥工業");
		IndustryTypeMap.put("02", "食品工業");
		IndustryTypeMap.put("03", "塑膠工業");
		IndustryTypeMap.put("04", "紡織纖維");
		IndustryTypeMap.put("05", "電機機械");
		IndustryTypeMap.put("06", "電器電纜");

		IndustryTypeMap.put("08", "玻璃陶瓷");
		IndustryTypeMap.put("09", "造紙工業");
		IndustryTypeMap.put("10", "鋼鐵工業");
		IndustryTypeMap.put("11", "橡膠工業");
		IndustryTypeMap.put("12", "汽車工業");
		IndustryTypeMap.put("13", "電子工業");
		IndustryTypeMap.put("14", "建材營造業");
		IndustryTypeMap.put("15", "航運業");
		IndustryTypeMap.put("16", "觀光事業");
		IndustryTypeMap.put("17", "金融保險業");
		IndustryTypeMap.put("18", "貿易百貨業");
		IndustryTypeMap.put("19", "綜合");
		IndustryTypeMap.put("20", "其他業");
		IndustryTypeMap.put("21", "化學工業");
		IndustryTypeMap.put("22", "生技醫療業");
		IndustryTypeMap.put("23", "油電燃氣業");
		IndustryTypeMap.put("24", "半導體業");
		IndustryTypeMap.put("25", "電腦及週邊設備業");
		IndustryTypeMap.put("26", "光電業");
		IndustryTypeMap.put("27", "通信網路業");
		IndustryTypeMap.put("28", "電子零組件業");
		IndustryTypeMap.put("29", "電子通路業");
		IndustryTypeMap.put("30", "資訊服務業");
		IndustryTypeMap.put("31", "其他電子業");
		IndustryTypeMap.put("32", "文化創意業");
		IndustryTypeMap.put("33", "農業科技業");
		IndustryTypeMap.put("34", "電子商務");
		return IndustryType.IndustryTypeMap;
		
	}
	
}	