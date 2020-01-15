package com.shyam.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.microservices.dto.CurrencyConversionDTO;

@RestController
public class CurrencyConversionControllerFeign {

	@Autowired
	private CurrencyExchangeServiceProxyUsingFeign feignProxy;

	@GetMapping("/currency-converter/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionDTO convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionDTO response = feignProxy.retrieveExchangeValue(from, to);

		return new CurrencyConversionDTO(response.getId(), response.getFrom(), response.getTo(),
				response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()),
				response.getPort());
	}

}
