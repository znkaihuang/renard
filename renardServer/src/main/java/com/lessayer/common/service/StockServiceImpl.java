package com.lessayer.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import com.lessayer.common.entity.Company;

@Service
public class StockServiceImpl implements StockService {
	
	private Optional<String> listedCompanyCache;
	
	public String retrieveAllListedCompany() throws IOException {
		
		URL url = new URL("https://openapi.twse.com.tw/v1/opendata/t187ap03_L");
		
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Cache-Control", "no-cache");
		connection.setRequestProperty("If-Modified-Since", "Mon, 26 Jul 1997 05:00:00 GMT");
		connection.setRequestProperty("Pragma", "no-cache");
		
		StringBuilder response = new StringBuilder(); 
		
		try (BufferedReader bufferedReader = new BufferedReader( new
		InputStreamReader(connection.getInputStream(), "utf-8"))) {
		
			String responseLine = null;
			
			while((responseLine = bufferedReader.readLine()) != null) {
			
				response.append(responseLine.trim());
			
			}
		
		}
		
		return response.toString();
		
	}
	
	public Company searchCompanyWithId() {
		
		Company company = new Company();
		return company;
		
	}
	
}
