package com.lessayer.common.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lessayer.common.service.StockService;

@RestController
@RequestMapping("/renardServer")
public class APIController {
	
	@Autowired
	StockService stockService;
	
	@GetMapping("/listedCompany")
	public String showListedCompany() throws IOException {
		
		return stockService.retrieveAllListedCompany();
		
	}
	
}
