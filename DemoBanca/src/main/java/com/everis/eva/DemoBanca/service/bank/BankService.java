package com.everis.eva.DemoBanca.service.bank;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.everis.eva.DemoBanca.service.bank.model.ClientData;
import com.everis.eva.DemoBanca.service.bank.model.RamdomNumber;


/**
 * Implementa la lógica de acceso a los servicios bank-end del banco
 * 
 * @author cbaospiq
 *
 */
//@Component // Generico
@Service
public class BankService implements IBankService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BankService.class);

	@Value("${bankservice.balance.url}")
	private String balanceUrl;
	
	@Value("${bankservice.client.info}")
	private String clientInfo;

	@Autowired
	private RestTemplate restTemplate;

	public Float queryBalanceFromAccount(String accoundId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

		ResponseEntity<List<RamdomNumber>> result = restTemplate.exchange(balanceUrl, HttpMethod.GET, requestEntity,
				new ParameterizedTypeReference<List<RamdomNumber>>() {
        });
		// restTemplate.getForEntity(url, responseType, uriVariables)

		LOGGER.debug("Getting result for Random service: " + result.getBody());

		return result.getBody().get(0).getRandom().floatValue();
	}

	
	@Override
	public List<ClientData> getBankClient(String clientId) {
		
		List<ClientData> list = new ArrayList<>();	
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

		ResponseEntity<ClientData> result = restTemplate.exchange(clientInfo, HttpMethod.GET, requestEntity, ClientData.class); 
		

		LOGGER.debug("Getting result client Service: " + result.getBody());
		
		list.add(result.getBody());
	
		return list;
	}
}
	