package com.example.webclientstudy;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	Logger log = LoggerFactory.getLogger(ApiService.class);
	private final ApiService apiService;
	private AtomicLong atomicLong = new AtomicLong();

	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}

	@GetMapping("/hello")
	public String hello() {
		final long l = atomicLong.addAndGet(1L);
		log.info("ApiController.hello(): count : {}", l);
		return "ok";
	}

	@GetMapping("/client")
	public String call() {
		apiService.service1();
		return "service1: OK";
	}

	@GetMapping("/client2")
	public String call2() {
		apiService.service2();
		return "service2: OK";
	}
}
