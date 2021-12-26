package com.shadowise.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(CircuitBreakerController.class);	
	
	
	@GetMapping("/sample-api")
	@Retry(name="sample-api", fallbackMethod = "hardCodedResponse")
//	@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
//	@RateLimiter(name="default")
//	@Bulkhead(name="sample-api")
	//10s => 10000 calls to the sample api
	public String sampleApi() {
		logger.info("Sample Api call Received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http:8080/some-dumy-url", String.class);
		return forEntity.getBody();
//		return "Sample- API";
	}
	
	public String hardCodedResponse(Exception ex) {
		return "fallback -response";
		}

}
