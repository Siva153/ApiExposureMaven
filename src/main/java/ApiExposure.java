

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apiexposure.utils.ApiCallable;

public class ApiExposure {

	public static void main(String[] args) throws ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		List<ApiCallable> apiCalls = new ArrayList();
		apiCalls.add(new ApiCallable("https://restcountries.com/v3.1/name/Martinique"));
		apiCalls.add(new ApiCallable("https://restcountries.com/v3.1/name/Barbados"));
		apiCalls.add(new ApiCallable("https://restcountries.com/v3.1/name/Serbia"));

		try {
			List<Future<String>> futureResults = executorService.invokeAll(apiCalls);
			for (Future<String> apiResponse : futureResults) {
				String response = apiResponse.get();
				System.out.println(response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			executorService.shutdown();
		}

	}

}
