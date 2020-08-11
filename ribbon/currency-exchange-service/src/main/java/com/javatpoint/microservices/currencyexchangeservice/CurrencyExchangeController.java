package com.javatpoint.microservices.currencyexchangeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController 
public class CurrencyExchangeController 
{
@Autowired
private Environment environment;
@Autowired
private ExchangeValueRepository repository;
@GetMapping("/currency-exchange/from/{from}/to/{to}")		//where {from} and {to} are path variable
public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to)   //from map to USD and to map to INR
{		
ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
//setting the port
exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
return exchangeValue;
}
}
