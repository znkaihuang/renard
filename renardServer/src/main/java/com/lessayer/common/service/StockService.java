package com.lessayer.common.service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.Stock;

public interface StockService {
	
	public static final LocalTime marketOpeningTime = LocalTime.parse("09:00:00");
	public static final LocalTime marketClosingTime = LocalTime.parse("13:30:00");
	
	public void retrieveAllListedCompanies() throws IOException;
	public List<Company> returnAllListedCompanies();
	public Optional<List<Company>> returnListCompanyByCompanyId(String companyId);
	public Optional<List<Company>> returnListCompanyByCompanyName(String companyName);
	public Optional<List<Stock>> returnStockByCompanyId(String companyId) throws IOException;
	
}
