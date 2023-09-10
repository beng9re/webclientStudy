package com.example.webclientstudy;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

@Service
public class ApiService {
	private final static Logger log = LoggerFactory.getLogger(ApiService.class);
	private final static String BASE_URL1 = "http://localhost:8081";
	private final static String BASE_URL2 = "http://localhost:8082";

	private final WebClient webClient;

	public ApiService(WebClient webClient) {
		this.webClient = webClient;
	}

	public void service1() {
		final WebClient clinet = webClient.mutate().baseUrl(BASE_URL1)
			.build();

		final String returnVal = clinet
			.get()
			.uri("/hello")
			.header("serviceMethod", BASE_URL1)
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}

	public void service2() {
		final WebClient clinet = webClient.mutate().baseUrl(BASE_URL2)
			.build();
		final String returnVal = clinet
			.get()
			.uri("/hello")
			.header("serviceMethod", BASE_URL2)
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}
}
