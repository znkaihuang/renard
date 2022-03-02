package com.lessayer.common.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.Stock;

public interface FormatConverter {
	
	public List<Company> convertJsonStringToCompanyClass(String jsonString)
			throws JsonMappingException, JsonProcessingException;
	public String convertCompanyClassToJsonString(List<Company> companies)
			throws JsonProcessingException;
	public List<Stock> convertJsonStringToStockClass(String jsonString)
			throws JsonMappingException, JsonProcessingException;
	public String convertStockClassToJsonString(List<Stock> stock)
			throws JsonProcessingException;
	
}
