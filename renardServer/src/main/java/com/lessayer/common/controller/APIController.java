package com.lessayer.common.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.Stock;
import com.lessayer.common.service.FormatConverter;
import com.lessayer.common.service.StockService;

@RestController
@RequestMapping("/renardServer")
public class APIController {
	
	@Autowired
	StockService stockService;
	@Autowired
	FormatConverter formatConverter;
	
	@GetMapping("/allCompanies")
	public String showListedCompanies() throws IOException {
		
		return formatConverter.convertCompanyClassToJsonString(
				stockService.returnAllListedCompanies());
		
	}
	
	@GetMapping("/companyId_{companyId}")
	public String showCompanyById(@PathVariable(value = "companyId") String companyId) 
			throws JsonProcessingException {
		
		Optional<List<Company>> companyOptional = 
				stockService.returnListCompanyByCompanyId(companyId);
		if(companyOptional.isEmpty()) {
			
			return "No such company ID!";
			
		}
		else {
			
			return formatConverter.convertCompanyClassToJsonString(companyOptional.get());
			
		}
		
	}
	
	@GetMapping("/companyName_{companyName}")
	public String showCompanyByName(@PathVariable(value = "companyName") String companyName) 
			throws JsonProcessingException {
		
		Optional<List<Company>> companyOptional = 
				stockService.returnListCompanyByCompanyName(companyName);
		if(companyOptional.isEmpty()) {
			
			return "No such company name!";
			
		}
		else {
			
			return formatConverter.convertCompanyClassToJsonString(companyOptional.get());
			
		}
		
	}
	
	@GetMapping("/instantInfo")
	public String showIntantInfo() {
		
		if(LocalTime.now().compareTo(StockService.marketOpeningTime) < 0) {
			
			return "Stock market opens at 9:00 and closes at 13:30."
					+ " Please try later.";
			
		}
		else if(LocalTime.now().compareTo(StockService.marketClosingTime) > 0) {
			
			return "Stock market opens at 9:00 and closes at 13:30."
					+ " Please try earlier tomorrow.";
			
		}
		else {
			
			return "good!";
			
		}
	}
	
	@GetMapping("/historyInfo")
	public String showHistoryInfo() throws IOException {
		
		Optional<List<Stock>> stockOptional = 
				stockService.returnStockByCompanyId("2330");
		if(stockOptional.isEmpty()) {
			
			return "No such company name!";
			
		}
		else {
			
			return formatConverter.convertStockClassToJsonString(stockOptional.get());
			
		}
		
	}
	
	@GetMapping("/currentDate")
	public String showCurrentDate() {
		
		return LocalDate.now().toString();
		
	}
	
	@GetMapping("/currentTime")
	public String showCurrentTime() {
		
		return LocalTime.now().toString();
		
	}
	
}
