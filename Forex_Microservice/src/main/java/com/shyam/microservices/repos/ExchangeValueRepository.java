package com.shyam.microservices.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shyam.microservices.entity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
	
	ExchangeValue findByFromAndTo(String from, String to);
}
