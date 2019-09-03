package com.ing.service;


import org.springframework.stereotype.Service;

import com.ing.dto.StatementResponseDto;
@Service
public interface StatementService {

	public StatementResponseDto getStatements(String accountNumber) ;

}
