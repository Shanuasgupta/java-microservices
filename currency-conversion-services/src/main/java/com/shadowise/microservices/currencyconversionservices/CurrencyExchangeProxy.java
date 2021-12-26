package com.shadowise.microservices.currencyconversionservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="currency-exchange",url="localhost:8000")//will stop load balancing due to URL given
@FeignClient(name="currency-exchange") //enable load balancing as URL removed. Known as Client side load Balancing 
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from, 
			@PathVariable String to);

}
