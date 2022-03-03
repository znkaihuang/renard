package com.lessayer.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.InstantStockInfo;
import com.lessayer.common.entity.Schedule;
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
	public String convertStockClassToJsonString(List<Stock> stock) 
			throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(stock);
	}

	@Override
	public List<InstantStockInfo> convertJsonStringToInstantStockInfoClass(String jsonString)
			throws JsonMappingException, JsonProcessingException {
		
		String startString = "\"msgArray\":";
		String endString ="],";
		int startIndex = jsonString.indexOf(startString) + startString.length();
		int endIndex = jsonString.indexOf(endString) + 1;
		return objectMapper.readValue(jsonString.substring(startIndex, endIndex), new 
				TypeReference<List<InstantStockInfo>>() {});
		
	}

	@Override
	public String convertInstantStockInfoClassToJsonString(
			List<InstantStockInfo> instantStockInfo)
			throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(instantStockInfo);
	}

	@Override
	public List<Schedule> convertJsonStringToScheduleClass(String jsonString)
			throws JsonMappingException, JsonProcessingException {
		
		return objectMapper.readValue(jsonString, new TypeReference<List<Schedule>>() {});
	}

	@Override
	public String convertScheduleClassToJsonString(List<Schedule> schedule) throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(schedule);
	}

	@Override
	public List<Stock> convertJsonStringToTotalIndex(String jsonString)
			throws JsonMappingException, JsonProcessingException {
		
		List<Stock> indexLists = new ArrayList<>();
		String startString = "\"data\":[";
		int start = jsonString.indexOf(startString) + startString.length();
		int end = jsonString.length() - 2;
		for(String indexByDay : jsonString.substring(start, end).split("]")) {
			
			Stock index = new Stock();
			String[] datas = indexByDay.substring(1).split("\"");
			index.setDate(datas[1]);
			index.setOpeningPrice(datas[3]);
			index.setHighestPrice(datas[5]);
			index.setLowestPrice(datas[7]);
			index.setClosingPrice(datas[9]);
			indexLists.add(index);
			
		}
		return indexLists;
		
	}

	@Override
	public String convertTotalIndexToJsonString(List<Stock> totalIndex) 
			throws JsonProcessingException {
		
		return objectMapper.writeValueAsString(totalIndex);
	}
	
}
