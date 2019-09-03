package com.ing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.dto.BalanceDto;
import com.ing.entity.Account;
import com.ing.entity.Mortagage;
import com.ing.exception.MortagageManagementException;
import com.ing.repository.AccountRepository;
import com.ing.repository.MortagageRepository;

/**
 * 
 * @author Sharath G S
 *
 */
@Service
public class BalanceServiceImpl implements BalanceService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(BalanceServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	MortagageRepository mortagageRepository;
	
	/**
	 * BalanceServiceImpl
	 * Sharath G S
	 */
	public BalanceDto getBalance(String customerId) {
		logger.info("balance service implementation class");
		
		BalanceDto balanceDto = new BalanceDto();
		
		Account accountDetails = accountRepository.findBycustomerId(customerId);
		
		Mortagage mortagagae = mortagageRepository.findBycustomerId(customerId);
		
		if(accountDetails.getAccountNumber() != null && mortagagae.getCustomerId() != null)
		{
			
			logger.info("balance service implementation class with customerId");
			balanceDto.setTransactionAccountNumber(accountDetails.getAccountNumber());
			balanceDto.setTransactionBalance(accountDetails.getBalance());
			balanceDto.setMortagageBalance(mortagagae.getMortagageBalance());
			balanceDto.setMortagageAccNumber(mortagagae.getMortagageId());
			balanceDto.setMessage("Balance Data");
			balanceDto.setStatus("SUCCESS");
			balanceDto.setStatusCode(200);
			return balanceDto;
		}else
		{
			logger.info("balance service implementation class with customerId does not exists");
			throw new MortagageManagementException("Customer Id does not exists or transactions not done");
		}
	}

}
