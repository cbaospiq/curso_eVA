package com.everis.eva.DemoBanca.service.bank;

import java.util.List;

import com.everis.eva.DemoBanca.service.bank.model.ClientData;

public interface IBankService {
	
	Float queryBalanceFromAccount(String accountId);
	
	List<ClientData> getBankClient(String clientId);
	


}
