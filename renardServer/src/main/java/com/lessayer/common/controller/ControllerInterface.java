package com.lessayer.common.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ControllerInterface {

	// @GetMapping("/allCompanies")
	public String showListedCompanies() throws IOException;
	// @GetMapping("/companyId_{companyId}")
	public String showCompanyById(@PathVariable(value = "companyId") String companyId) 
			throws JsonProcessingException;
	// @GetMapping("/companyName_{companyName}")
	public String showCompanyByName(@PathVariable(value = "companyName") String companyName) 
			throws JsonProcessingException;
	// @GetMapping("/instantInfo_{companyId}")
	public String showIntantInfo(@PathVariable(value = "companyId") String companyId)
			throws IOException;
	// @GetMapping("/instantAggreInfo")
	public String showInstantAggregateInfo() throws IOException;
	// @GetMapping("/historyInfo_{date}_{companyId}")
	public String showHistoryInfo(@PathVariable(value = "date") String date,
			@PathVariable(value = "companyId") String companyId) 
					throws IOException;
	// @GetMapping("/currentDate")
	public String showCurrentDate();
	// @GetMapping("/currentTime")
	public String showCurrentTime();
	// @GetMapping("/holidaySchedule")
	public String showHolidaySchedule() throws JsonProcessingException;
	// @GetMapping("/totalIndexHistory_{date}")
	public String showTotalIndexHistory(@PathVariable(value = "date") String date) 
			throws IOException;
	// @GetMapping("/allListedCompaniesDailyInfo")
	public String showAllListedCompaniesDailyInfo() throws IOException;
	// @GetMapping("/lastModifiedDateOfCompaniesDailyInfo")
	public String returnLastModifiedDateOfAllCompaniesDailyInfo() 
			throws IOException, ParseException;
	// @GetMapping("/industryType")
	public String showIndustryType();
	
}
