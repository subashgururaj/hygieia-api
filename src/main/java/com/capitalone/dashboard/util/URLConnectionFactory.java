package com.capitalone.dashboard.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

@Component
public class URLConnectionFactory {

	@Autowired
	public URLConnectionFactory() {

	}

	public HttpURLConnection get(URL url, Proxy proxy) throws IOException {
		HttpURLConnection connection = proxy != null ?
				(HttpURLConnection) url.openConnection(proxy) :
				(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		return connection;
	}
}
