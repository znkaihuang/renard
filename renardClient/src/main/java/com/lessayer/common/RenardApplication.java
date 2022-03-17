package com.lessayer.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lessayer.common.entity.Transaction;
import com.lessayer.common.repository.TransactionRepository;
import com.lessayer.common.service.StorageStockService;
import com.lessayer.common.service.TransactionService;

@SpringBootApplication
public class RenardApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionService service;

	public static void main(String[] args) {

		SpringApplication.run(RenardApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		
		/*
		 * logger.info("Starting renard ..."); URL url = new
		 * URL("https://openapi.twse.com.tw/v1/exchangeReport/STOCK_DAY_ALL");
		 * HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		 * connection.setRequestMethod("GET"); logger.info("Header {}" ,
		 * connection.getHeaderField("last-modified"));
		 */
		  
			/*
			 * StringBuilder content = new StringBuilder(); try(BufferedReader
			 * bufferedReader = new BufferedReader( new
			 * InputStreamReader(connection.getInputStream(), "utf-8"))) {
			 * 
			 * 
			 * String string = null; while((string = bufferedReader.readLine()) != null) {
			 * content.append(string);
			 * 
			 * }
			 * 
			 * } logger.info(content.toString());
			 */

			/*
			 * service.createTransaction("20220316", "1101 台泥", 49.5, 2000, "買進");
			 * service.createTransaction("20220316", "1102 亞泥", 33.2, 2000, "買進");
			 * service.createTransaction("20220316", "1103 嘉泥", 10.0, 1000, "買進");
			 * service.createTransaction("20220317", "1101 台泥", 50.0, 2000, "買進");
			 * service.createTransaction("20220317", "1101 台泥", 50.2, 1000, "賣出");
			 * service.createTransaction("20220317", "1102 亞泥", 33.0, 1000, "賣出");
			 * service.createTransaction("20220318", "1103 嘉泥", 10.3, 1000, "賣出");
			 * logger.info("Latest transaction {}",
			 * service.returnLatestTransaction().toString());
			 * logger.info("Total sell profit {}", service.calculateTotalSellProfit());
			 */

	}

}
