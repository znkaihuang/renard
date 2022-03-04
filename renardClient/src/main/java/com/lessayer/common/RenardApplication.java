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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenardApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		
		SpringApplication.run(RenardApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Starting renard ...");
		URL url = new URL("http://localhost:8081/renardServer/holidaySchedule");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		StringBuilder content = new StringBuilder();
		try(BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			
			
			String string = null;
			while((string = bufferedReader.readLine()) != null) {
				content.append(string);
				
			}
			
		}
		logger.info(content.toString());
		
		
		
		
		
	}

}
