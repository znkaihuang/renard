package com.lessayer.common.service;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public interface ConnectionService {
	
	public HttpsURLConnection getResponseContent(URL url);
	
}
