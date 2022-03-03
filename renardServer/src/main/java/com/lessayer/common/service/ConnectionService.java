package com.lessayer.common.service;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public interface ConnectionService {
	
	public String getResponseContent(URL url) throws IOException;
	public String getResponseContentWithPathVar(URL url, Map<String, String> pathVar)
		throws IOException;
	
}
