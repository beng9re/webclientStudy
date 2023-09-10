package com.example.webclientstudy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class CustomWebClient {
	Logger logger = LoggerFactory.getLogger(CustomWebClient.class);

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.filter(logRequest())
			.defaultHeader("Content-Type", "application/json")
			.build();


	}

	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			StringBuilder sb = new StringBuilder();
			sb.append("url: ")
				.append(clientRequest.url())
				.append(" service :")
				// 요청한 메서드
				.append(clientRequest.headers().get("serviceMethod"));

			logger.info(sb.toString());

			return Mono.just(clientRequest);
		});
	}



}
