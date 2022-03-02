package com.lessayer.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.Stock;

@Service
public class FormatConverterImpl implements FormatConverter {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Company> convertJsonStringToCompanyClass(String jsonString) 
			throws JsonMappingException, JsonProcessingException {
		
		return objectMapper.readValue(jsonString, new TypeReference<List<Company>>(){});
		
	}

	@Override
	public String convertCompanyClassToJsonString(List<Company> companies) 
			throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(companies);
	}

	@Override
	public List<Stock> convertJsonStringToStockClass(String jsonString)
			throws JsonMappingException, JsonProcessingException {
		
		List<Stock> stockLists = new ArrayList<>();
		String startString = "\"data\":[";
		String endString = "],\"notes\":";
		int start = jsonString.indexOf(startString) + startString.length();
		int end = jsonString.indexOf(endString);
		for(String stockByDay : jsonString.substring(start, end).split("]")) {
			
			Stock stock = new Stock();
			String[] datas = stockByDay.substring(1).split("\"");
			stock.setDate(datas[1]);
			stock.setTradeVolume(datas[3]);
			stock.setTradeValue(datas[5]);
			stock.setOpeningPrice(datas[7]);
			stock.setHighestPrice(datas[9]);
			stock.setLowestPrice(datas[11]);
			stock.setClosingPrice(datas[13]);
			stock.setChange(datas[15]);
			stock.setTransaction(datas[datas.length-1]);
			stockLists.add(stock);
			
		}
		return stockLists;
		
	}

	@Override
	public String convertStockClassToJsonString(List<Stock> stock) throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(stock);
	}
	
}
