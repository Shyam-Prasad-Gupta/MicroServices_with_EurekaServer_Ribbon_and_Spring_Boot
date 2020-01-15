package com.shyam.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shyam.microservices.dto.CurrencyConversionDTO;

@RestController
public class CurrencyConversionController {

	@GetMapping("/")
	public void justCheck() {
		System.out.println("Hi");
	}

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionDTO convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionDTO> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionDTO.class, uriVariables);

		CurrencyConversionDTO currencyConversionDTO = responseEntity.getBody();
		return new CurrencyConversionDTO(currencyConversionDTO.getId(), currencyConversionDTO.getFrom(),
				currencyConversionDTO.getTo(), currencyConversionDTO.getConversionMultiple(),
				quantity, quantity.multiply(currencyConversionDTO.getConversionMultiple()),
				currencyConversionDTO.getPort());
	}
}
