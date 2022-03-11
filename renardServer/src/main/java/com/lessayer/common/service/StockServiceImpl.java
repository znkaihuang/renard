package com.lessayer.common.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.InstantStockInfo;
import com.lessayer.common.entity.Schedule;
import com.lessayer.common.entity.Stock;


@Service
public class StockServiceImpl implements StockService {
	
	private List<Company> listedCompanies;
	private List<Schedule> holidaySchedule;
	
	@Autowired
	private ConnectionService connectionService;
	@Autowired
	private FormatConverter formatConverter;
	
	@Value("${link.twseopenapi.listedcompanyinfo}")
	private String twseopenapilistedcompanyURL;
	@Value("${link.twse.stockhistoryinfo}")
	private String twsestockhistroyURL;
	@Value("${link.twse.instantinfo}")
	private String twseinstantinfoURL;
	@Value("${link.twseopenapi.holidayschedule}")
	private String twseopenapiholidayscheduleURL;
	@Value("${link.twse.totalindexhistory}")
	private String twsetotalindexhistoryURL;
	@Value("${link.twseopenapi.listedcompanydailyinfo}")
	private String twseopenapilistedcompanyinfoURL;
	
	@PostConstruct
	private void initService() throws IOException {
		
		retrieveAllListedCompanies();
		retrieveHolidaySchedule();
		
	}
	
	@Override
	public void retrieveAllListedCompanies() throws IOException {
			
		URL url = new URL(twseopenapilistedcompanyURL);
		String responseContent = connectionService.getResponseContent(url);
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
	public Optional<List<Stock>> returnStockByDateAndCompanyId(String date, String companyId) 
			throws IOException {
		
		URL url = new URL(twsestockhistroyURL);
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("response", "json");
		pathVar.put("date", date);
		pathVar.put("stockNo", companyId);
		String responseContent = connectionService.getResponseContentWithPathVar(url, pathVar);
		List<Stock> stock = formatConverter.convertJsonStringToStockClass(responseContent, "twse");
		return Optional.ofNullable(stock);
		
	}

	@Override
	public Optional<List<InstantStockInfo>> returnStockInstantInfoByCompanyId(String companyId) 
			throws IOException {
		
		URL url = new URL(twseinstantinfoURL);
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("ex_ch", "tse_" + companyId + ".tw");
		pathVar.put("json", "1");
		pathVar.put("delay", "0");
		String responseContent = connectionService.getResponseContentWithPathVar(url, pathVar);
		List<InstantStockInfo> instantInfo = 
				formatConverter.convertJsonStringToInstantStockInfoClass(responseContent);
		return Optional.ofNullable(instantInfo);
		
	}

	@Override
	public void retrieveHolidaySchedule() throws IOException {
		
		URL url = new URL(twseopenapiholidayscheduleURL);
		String responseContent = connectionService.getResponseContent(url);
		holidaySchedule = formatConverter.convertJsonStringToScheduleClass(responseContent);
		
	}

	@Override
	public List<Schedule> returnHolidaySchedule() {
		
		return this.holidaySchedule;
	}

	@Override
	public Optional<List<Stock>> returnTotalIndexByDate(String date) throws IOException {
		
		URL url = new URL(twsetotalindexhistoryURL);
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("response", "json");
		pathVar.put("date", date);
		String responseContent = connectionService.getResponseContentWithPathVar(url, pathVar);
		return Optional.ofNullable(
				formatConverter.convertJsonStringToTotalIndex(responseContent));
		
	}

	@Override
	public Optional<List<Stock>> returnAllCompaniesDailyInfo() throws IOException {
		
		URL url = new URL(twseopenapilistedcompanyinfoURL);
		String responseContent = connectionService.getResponseContent(url);
		return Optional.ofNullable(
				formatConverter.convertJsonStringToStockClass(responseContent, "twseopenapi"));
	}
	
}
