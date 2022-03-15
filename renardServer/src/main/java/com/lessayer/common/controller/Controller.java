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
public class Controller implements ControllerInterface {
	
	@Autowired
	private StockService stockService;
	@Autowired
	private FormatConverter formatConverter;
	
	private LocalTime lastRequestTime;
	
	@GetMapping("/allCompanies")
	public String showListedCompanies() throws IOException {
		
		return formatConverter.convertCompanyClassToJsonString(
				stockService.returnAllListedCompanies());
		
	}
	
	@GetMapping("/companyId_{companyId}")
	public String showCompanyById(@PathVariable(value = "companyId") String companyId) 
			throws JsonProcessingException {
		
		lastRequestTime = LocalTime.now();
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
		
		lastRequestTime = LocalTime.now();
		Optional<List<Company>> companyOptional = 
				stockService.returnListCompanyByCompanyName(companyName);
		if(companyOptional.isEmpty()) {
			
			return "No such company name!";
			
		}
		else {
			
			return formatConverter.convertCompanyClassToJsonString(companyOptional.get());
			
		}
		
	}
	
	@GetMapping("/instantInfo_{companyId}")
	public String showIntantInfo(@PathVariable(value = "companyId") String companyId)
			throws IOException {
		
		if(LocalTime.now().compareTo(StockService.marketOpeningTime) < 0) {
			
			return "Stock market opens at 9:00 and closes at 13:30."
					+ " Please try later.";
			
		}
		else {
			
			if(isRequestAvail()) {
				
				return formatConverter.convertInstantStockInfoClassToJsonString(
						stockService.returnStockInstantInfoByCompanyId(companyId).get());
			
			}
			else {

				String warnMessage = "Your previous request is at " 
						+ lastRequestTime.toString()
						+ ". Please send another request 5 seconds later.";
				return warnMessage;
					
			}
		}
	}
	
	@GetMapping("/instantAggreInfo")
	public String showInstantAggregateInfo() throws IOException {
		
		return showIntantInfo("t00");
		
	}
	
	@GetMapping("/historyInfo_{date}_{companyId}")
	public String showHistoryInfo(@PathVariable(value = "date") String date,
			@PathVariable(value = "companyId") String companyId) 
					throws IOException {
		
		lastRequestTime = LocalTime.now();
		Optional<List<Stock>> stockOptional = 
				stockService.returnStockByDateAndCompanyId(date, companyId);
		if(stockOptional.isEmpty()) {
			
			return "Bad format!";
			
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
	
	@GetMapping("/holidaySchedule")
	public String showHolidaySchedule() throws JsonProcessingException {
		
		return formatConverter.convertScheduleClassToJsonString(
				stockService.returnHolidaySchedule());
		
	}
	
	@GetMapping("/totalIndexHistory_{date}")
	public String showTotalIndexHistory(@PathVariable(value = "date") String date) 
			throws IOException {
		
		return formatConverter.convertTotalIndexToJsonString(
				stockService.returnTotalIndexByDate(date).get());
		
	}
	
	public String createPrevMonthString(String date) {
		
		StringBuilder returnedString = new StringBuilder();
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6)) - 1;
		if(month < 1) {
			month = 12;
			year -= 1;
		}
		if(month < 10) {
			returnedString.append(year).append("0").append(month).append("01");
		}
		else {
			returnedString.append(year).append(month).append("01");
		}
		return returnedString.toString();
		
	}

	@GetMapping("/allListedCompaniesDailyInfo")
	public String showAllListedCompaniesDailyInfo() throws IOException {
		
		return formatConverter.convertStockClassToJsonString(
				stockService.returnAllCompaniesDailyInfo().get());
	}
	
	public boolean isRequestAvail() {
		
		if(Optional.ofNullable(lastRequestTime).isEmpty()) {
			
			lastRequestTime = LocalTime.now();
			return true;
			
		}
		
		if(lastRequestTime.plusSeconds(5).isBefore(LocalTime.now())) {
			
			lastRequestTime = LocalTime.now();
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}

	@GetMapping("/industryType")
	public String showIndustryType() {
		
		return stockService.returnIndustryTypeMap().toString();
	}
	
}
