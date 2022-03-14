package com.lessayer.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/home")
	public String displayHomePage() {
		
		return "index";
		
	}
	
	@GetMapping("/companyStockHistory")
	public String displayCompanStockHistory() {
		
		return "companyStockHistory";
		
	}
	
	@GetMapping("/companyInfo")
	public String displayComapnInfoHistory() {
		
		return "companyInfo";
		
	}
	
	@GetMapping("/orderSystem")
	public String displayOrderSystem() {
		
		return "orderSystem";
		
	}
	
}
