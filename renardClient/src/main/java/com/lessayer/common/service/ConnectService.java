package com.lessayer.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Service;

@Service
public class ConnectService {

	public String getResponseContent(URL url) throws IOException {
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		
		return readResponseAsString(connection);
	}
	
	public String getResponseContentWithPathVar(URL url, Map<String, String> pathVar) 
			throws IOException {
		
		StringBuilder urlString = new StringBuilder(url.toString());
		urlString.append("?");
		pathVar.forEach((Key, Value) -> {
			urlString.append(Key + "=" + Value + "&");
		});
		urlString.deleteCharAt(urlString.length() - 1);
		URL targetURL = new URL(urlString.toString());
		return getResponseContent(targetURL);
	
	}
	
	private String readResponseAsString(HttpURLConnection connection) throws IOException {
		
		StringBuilder response = new StringBuilder(); 
		
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), "utf-8"))) {
		
			String responseLine = null;	
			while((responseLine = bufferedReader.readLine()) != null) {
			
				response.append(responseLine.trim());
			
			}
			
		}
		
		return response.toString();
		
	}

	public String getResponseHeader(URL url, String headerField) throws IOException {
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		
		return connection.getHeaderField(headerField);
		
	}
	
}
