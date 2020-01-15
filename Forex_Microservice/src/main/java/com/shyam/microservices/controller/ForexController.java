package com.shyam.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shyam.microservices.entity.ExchangeValue;
import com.shyam.microservices.repos.ExchangeValueRepository;

@RestController
public class ForexController {

	@Autowired
	private Environment env;

	@Autowired
	private ExchangeValueRepository exchangeValueRepo;
	
	@GetMapping("/")
	public void justCheck(){
		System.out.println("Hi");
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = exchangeValueRepo.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return exchangeValue;
	}

}
