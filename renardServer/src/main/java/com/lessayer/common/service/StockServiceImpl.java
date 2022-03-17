package com.lessayer.common.service;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lessayer.common.entity.Company;
import com.lessayer.common.entity.IndustryType;
import com.lessayer.common.entity.InstantStockInfo;
import com.lessayer.common.entity.Schedule;
import com.lessayer.common.entity.Stock;


@Service
public class StockServiceImpl implements StockService {
	
	private List<Company> listedCompanies;
	private List<Schedule> holidaySchedule;
	private List<Stock> totalIndex;
	private Date currentDate = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private DateFormat gmtDateFormat = 
			new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
	private Map<String, String> industryMap = IndustryType.returnIndustryTypMap();
	
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
		retrieveTotalIndex();
		
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
		
		String[] lastHalfYearMonth = createLastHalfYearString(date);
		List<Stock> historyIndex = new ArrayList<Stock>();
		URL url = new URL(twsestockhistroyURL);
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("response", "json");
		pathVar.put("stockNo", companyId);
		for(int i = 0; i < lastHalfYearMonth.length; i++) {
			
			pathVar.put("date", lastHalfYearMonth[i]);
			String responseContent = connectionService.
					getResponseContentWithPathVar(url, pathVar);
			historyIndex.addAll(formatConverter
					.convertJsonStringToStockClass(responseContent, "twse"));
			
		}
		
		return Optional.ofNullable(historyIndex);
		
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
	public void retrieveTotalIndex() throws IOException {
		
		String[] lastHalfYearMonth = createLastHalfYearString(dateFormat.format(currentDate));
		URL url = new URL(twsetotalindexhistoryURL);
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("response", "json");
		totalIndex = new ArrayList<Stock>();
		
		for(int i = 0; i < lastHalfYearMonth.length; i++) {
			
			pathVar.put("date", lastHalfYearMonth[i]);
			String responseContent = connectionService.
					getResponseContentWithPathVar(url, pathVar);
			totalIndex.addAll(formatConverter.convertJsonStringToTotalIndex(responseContent));
			
		}
		
	}
	
	@Override
	public Optional<List<Stock>> returnTotalIndexByDate(String date) throws IOException {
		
		return Optional.ofNullable(totalIndex);
		
	}

	@Override
	public Optional<List<Stock>> returnAllCompaniesDailyInfo() throws IOException {
		
		URL url = new URL(twseopenapilistedcompanyinfoURL);
		String responseContent = connectionService.getResponseContent(url);
		return Optional.ofNullable(
				formatConverter.convertJsonStringToStockClass(responseContent, "twseopenapi"));
	}
	
	@Override
	public String returnLastModifiedDateOfAllCompaniesDailyInfo() 
			throws IOException, ParseException {
		
		URL url = new URL(twseopenapilistedcompanyinfoURL);
		
		String dateString = connectionService.getResponseHeader(url, "last-modified");
		return dateFormat.format(gmtDateFormat.parse(dateString)).toString();
		
	}
	
	public String[] createLastHalfYearString(String date) {
		
		String[] halfYearStrings = new String[6];
		halfYearStrings[5] = date;
		for(int i = 4; i >= 0; i--) {
			halfYearStrings[i] = createLastMonthString(halfYearStrings[i + 1]);
		}
		return halfYearStrings;
		
	}
	
	public String createLastMonthString(String date) {
		
		StringBuilder lastMonthDateString = new StringBuilder();
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6)) - 1;
		if(month < 1) {
			month = 12;
			year -= 1;
		}
		if(month < 10) {
			
			return lastMonthDateString.append(year).append("0").append(month)
					.append("01").toString();
		
		}
		else {
			
			return lastMonthDateString.append(year).append(month).append("01").toString();
			
		}
		
	}

	@Override
	public Map<String, String> returnIndustryTypeMap() {
		
		return this.industryMap;
	}
	
}
