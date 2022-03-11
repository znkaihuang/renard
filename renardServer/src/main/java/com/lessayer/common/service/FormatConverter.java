package com.lessayer.common.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.InstantStockInfo;
import com.lessayer.common.entity.Schedule;
import com.lessayer.common.entity.Stock;

public interface FormatConverter {
	
	public List<Company> convertJsonStringToCompanyClass(String jsonString)
			throws JsonMappingException, JsonProcessingException;
	public String convertCompanyClassToJsonString(List<Company> companies)
			throws JsonProcessingException;
	public List<Stock> convertJsonStringToStockClass(String jsonString, String source)
			throws JsonMappingException, JsonProcessingException;
	public String convertStockClassToJsonString(List<Stock> stock)
			throws JsonProcessingException;
	public List<InstantStockInfo> convertJsonStringToInstantStockInfoClass(String jsonString)
			throws JsonMappingException, JsonProcessingException;
	public String convertInstantStockInfoClassToJsonString(List<InstantStockInfo> instantStockInfo)
			throws JsonProcessingException;
	public List<Schedule> convertJsonStringToScheduleClass(String jsonString)
			throws JsonMappingException, JsonProcessingException;
	public String convertScheduleClassToJsonString(List<Schedule> schedule)
			throws JsonProcessingException;
	public List<Stock> convertJsonStringToTotalIndex(String jsonString)
			throws JsonMappingException, JsonProcessingException;
	public String convertTotalIndexToJsonString(List<Stock> totalIndex)
			throws JsonProcessingException;
	
}
