package com.shyam.microservices.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shyam.microservices.dto.CurrencyConversionDTO;

@FeignClient(name = "forex-service")
@RibbonClient(name = "forex-service")
public interface CurrencyExchangeServiceProxyUsingFeignAndRibbonAndEureka {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionDTO retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
}
