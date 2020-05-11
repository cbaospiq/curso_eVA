package com.everis.eva.DemoBanca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everis.eva.DemoBanca.service.bank.IBankService;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SaldoController  {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(SaldoController.class);
	
	@Autowired
	private IBankService bankService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/balance/account/{accountId}")
	public Float queryBalanceAccount(@PathVariable String accountId ) {
		LOGGER.info("Getting balance account for: " + accountId);
		return bankService.queryBalanceFromAccount(accountId);
		
	}
	


	
}
