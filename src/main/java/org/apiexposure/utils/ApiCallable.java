package org.apiexposure.utils;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ApiCallable implements Callable<String> {

	private String apiUrl;

	public ApiCallable(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String call() throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(apiUrl);
		CloseableHttpResponse httpResponse = httpClient.execute(get);
		HttpEntity httpEntity = httpResponse.getEntity();
		String response = EntityUtils.toString(httpEntity);
		httpClient.close();
		return response;
	}

}
