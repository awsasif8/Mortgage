package com.ing.service;

import org.springframework.stereotype.Service;
import com.ing.dto.BalanceDto;

@Service
public interface BalanceService {

	public BalanceDto getBalance(String customerId);
	
}
