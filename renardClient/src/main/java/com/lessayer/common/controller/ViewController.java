package com.lessayer.common.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lessayer.common.entity.StorageStock;
import com.lessayer.common.service.ConnectService;
import com.lessayer.common.service.StorageStockService;
import com.lessayer.common.service.TransactionService;

@Controller
public class ViewController {
	
	@Autowired
	private StorageStockService stockService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private ConnectService connectService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String companyInfoLastModifiedURL = 
			"http://localhost:8081/renardServer/lastModifiedDateOfCompaniesDailyInfo";
	
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
	public String displayOrderSystem(ModelMap model) throws MalformedURLException, IOException {
		
		List<StorageStock> storageStocks = stockService.returnAllStorageStocks();
		List<Double> stockPrices = returnStockPriceStrings(storageStocks);
		Double stockTotalValue = calculateStockValue(storageStocks, stockPrices);
		Optional<Double> transactionProfitOptional = 
				Optional.ofNullable(transactionService.calculateTotalSellProfit());
		model.put("storageStocks", storageStocks);
		model.put("transactions", transactionService.returnAllTransactions());
		model.put("stockPrices", stockPrices);
		model.put("stockTotalValue", stockTotalValue);
		model.put("transactionProfit", 
				(transactionProfitOptional.isEmpty()) ? 0 : transactionProfitOptional.get());
		model.put("updateDate", connectService
				.getResponseContent(new URL(companyInfoLastModifiedURL)));
		logger.info("updateDate {}", connectService
				.getResponseContent(new URL(companyInfoLastModifiedURL)));
		return "orderSystem";
		
	}
	
	@PostMapping("/orderSystem")
	public String dealOrder(@RequestParam String stockId, @RequestParam Double price,
			@RequestParam Integer volume, String buyButton, String sellButton) {
		
		logger.info("Post request to /orderSystem");
		logger.info("stockid {}, price {}, volume {}, buyButton {}, sellButton {}",
				stockId, price, volume, buyButton, sellButton);
		String buyOrSell = (Optional.ofNullable(buyButton).isPresent()) ? "買進" : "賣出";
		transactionService
			.createTransaction(LocalDate.now().toString(), stockId, price, volume, buyOrSell);
		
		return "redirect:/orderSystem";
	}
	
	private List<Double> returnStockPriceStrings(List<StorageStock> stocks)
			throws MalformedURLException, IOException {
		
		List<Double> stockPriceList = new ArrayList<Double>();
		
		for(StorageStock stock : stocks) {
			
			String stockId = stock.getStockId().split(" ")[0];
			stockPriceList.add(Double.parseDouble(connectService.getResponseContent(
					new URL("http://localhost:8081/renardServer/companyDailyPrice_" + stockId))
					));
			
		}
		return stockPriceList;
		
	}
	
	private Double calculateStockValue(List<StorageStock> stocks, List<Double> priceStrings) {
		
		Double value = 0.0;
		for(int i = 0; i < stocks.size(); i++) {
			
			value += stocks.get(i).getVolume() * priceStrings.get(i);
			
		}
		return value;
		
	}
	
}
