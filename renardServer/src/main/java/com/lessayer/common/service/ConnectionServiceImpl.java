package com.lessayer.common.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import org.springframework.stereotype.Service;

@Service
public class ConnectionServiceImpl implements ConnectionService {

	@Override
	public String getResponseContent(URL url) throws IOException {
		
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Cache-Control", "no-cache");
		connection.setRequestProperty("If-Modified-Since", "Mon, 26 Jul 1997 05:00:00 GMT");
		connection.setRequestProperty("Pragma", "no-cache");
		
		return readResponseAsString(connection);
	}
	
	@Override
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
	
	private String readResponseAsString(HttpsURLConnection connection) throws IOException {
		
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

	@Override
	public String getResponseHeader(URL url, String headerField) throws IOException {
		
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Cache-Control", "no-cache");
		connection.setRequestProperty("If-Modified-Since", "Mon, 26 Jul 1997 05:00:00 GMT");
		connection.setRequestProperty("Pragma", "no-cache");
		
		return connection.getHeaderField(headerField);
		
	}
	
}
