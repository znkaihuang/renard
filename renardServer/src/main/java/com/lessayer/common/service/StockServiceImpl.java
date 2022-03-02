package com.lessayer.common.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.Stock;


@Service
public class StockServiceImpl implements StockService {
	
	private List<Company> listedCompanies;
	
	@Autowired
	ConnectionService connection;
	@Autowired
	FormatConverter formatConverter;
	
	@Value("${link.twseopenapi.listedcompanyinfo}")
	String twseopenapiURL;
	
	@PostConstruct
	private void initService() throws IOException {
		
		retrieveAllListedCompanies();
		
	}
	
	@Override
	public void retrieveAllListedCompanies() throws IOException {
			
		URL url = new URL(twseopenapiURL);
		String responseContent = connection.getResponseContent(url);
		listedCompanies = formatConverter.convertJsonStringToCompanyClass(responseContent);
		
	}

	@Override
	public List<Company> returnAllListedCompanies() {
		
		return this.listedCompanies;
	}

	@Override
	public Optional<List<Company>> returnListCompanyByCompanyId(String companyId) {
		
		List<Company> target = null;
		for(Company company : this.listedCompanies) {
			
			if(company.getCompanyId().equals(companyId)) {
				
				target = Arrays.asList(company);
				break;
				
			}
			
		}
		return Optional.ofNullable(target);
	}

	@Override
	public Optional<List<Company>> returnListCompanyByCompanyName(String companyName) {
		
		List<Company> target = null;
		for(Company company : this.listedCompanies) {
			
			if(company.getCompanyName().equals(companyName) ||
					company.getCompanyAbbrev().equals(companyName) ||
					company.getSymbol().equalsIgnoreCase(companyName)) {
				
				target = Arrays.asList(company);
				break;
				
			}
			
		}
		return Optional.ofNullable(target);
	}

	@Override
	public Optional<List<Stock>> returnStockByCompanyId(String companyId) throws IOException {
		
		URL url = new URL("https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=20220112&stockNo=2330");
		String responseContent = connection.getResponseContent(url);
		List<Stock> stock = formatConverter.convertJsonStringToStockClass(responseContent);
		return Optional.ofNullable(stock);
		
	}
	
}
