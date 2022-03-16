package com.lessayer.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	@PostMapping("/orderSystem")
	public void dealOrder(@RequestParam String stockId, @RequestParam String price,
			@RequestParam String volume, String buyButton, String sellButton) {
		
		logger.info("Post request to /orderSystem");
		logger.info("stockid {}, price {}, volume {}, buyButton {}, sellButton {}",
				stockId, price, volume, buyButton, sellButton);
		
	}
	
}
