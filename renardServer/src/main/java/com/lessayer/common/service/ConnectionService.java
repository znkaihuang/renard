package com.lessayer.common.service;

import java.io.IOException;
import java.net.URL;

public interface ConnectionService {
	
	public String getResponseContent(URL url) throws IOException;
	
}
