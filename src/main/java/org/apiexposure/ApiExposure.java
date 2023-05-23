package org.apiexposure;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apiexposure.utils.ApiCallable;

public class ApiExposure {

	public static void main(String[] args) throws ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Future<String> apiFuture1 = executorService
				.submit(new ApiCallable("https://restcountries.com/v3.1/name/Martinique"));
		Future<String> apiFuture2 = executorService
				.submit(new ApiCallable("https://restcountries.com/v3.1/name/Barbados"));
		Future<String> apiFuture3 = executorService
				.submit(new ApiCallable("https://restcountries.com/v3.1/name/Serbia"));
		try {
			System.out.println("Api Future1  output: " + apiFuture1.get());
			System.out.println("=================================");
			System.out.println("Api Future2  output: " + apiFuture2.get());
			System.out.println("=================================");
			System.out.println("Api Future3  output: " + apiFuture3.get());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		executorService.shutdown();

	}

}
