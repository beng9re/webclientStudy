package com.example.webclientstudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class ApiControllerTest {

	private final static int MAX_THREAD = 200;
	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	void test() throws InterruptedException {
		//쓰레드를 생성한다.
		ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

		//쓰레드들을 대기하기위해 생성해놓는다.
		CountDownLatch countDownLatch = new CountDownLatch(MAX_THREAD);

		//when
		for (int i = 0; i < MAX_THREAD; i++) {
			//멀티쓰레드를 요청하고
			if (i % 2 == 0) {
				executorService.execute(() -> {
					restTemplate.getForObject("http://localhost:8080/client", String.class);
					countDownLatch.countDown();
				});
			} else {
				executorService.execute(() -> {
					restTemplate.getForObject("http://localhost:8080/client2", String.class);
					countDownLatch.countDown();
				});
			}

		}

		// countDownLatch가 완료 되길까지 대기한다.
		countDownLatch.await();
	}
}