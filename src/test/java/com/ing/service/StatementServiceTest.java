package com.ing.service;

import static org.junit.Assert.assertEquals;



import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ing.dto.StatementDto;
import com.ing.dto.StatementResponseDto;
import com.ing.entity.Transaction;

import com.ing.repository.StatementRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StatementServiceTest {

	@Mock
	StatementRepository statementRepository;

	@InjectMocks
	StatementServiceImpl statementService;

	@Test
	public void getStatementsTestPositive() {
		StatementDto dto1 = new StatementDto();
		dto1.setDescription("payment towards");
		dto1.setTransactionAmount(200.0);
	
		dto1.setTransactionId(1234l);
		dto1.setTransactionType("credit");
		List<StatementDto> dto3 = new ArrayList<StatementDto>();
		dto3.add(dto1);
		StatementResponseDto dto = new StatementResponseDto();
		dto.setData(dto3);

		dto.setMessage("details displayed successfully");
		dto.setStatusCode(200);
		dto.setStatus("SUCCESS");
String accountNumber="12345";
		Pageable pageable = PageRequest.of(0, 1);
		Transaction transactions = new Transaction();
		transactions.setAccountNumber("12345");

	
		
		List<Transaction> transaction = new ArrayList<Transaction>();
		transaction.add(transactions);
		

		

		
	
Mockito.when(statementRepository.findByAccountNumber(accountNumber, pageable)).thenReturn(transaction);	
StatementResponseDto dto5 = statementService.getStatements(accountNumber);
assertEquals(dto.getStatusCode(), dto5.getStatusCode());
		

		
	}
	@Test
	public void getStatementsTestNegative() {
		StatementDto dto1 = new StatementDto();
		dto1.setDescription("payment towards");
		dto1.setTransactionAmount(200.0);
	
		dto1.setTransactionId(1234l);
		dto1.setTransactionType("credit");
		List<StatementDto> dto3 = new ArrayList<StatementDto>();
		dto3.add(dto1);
		StatementResponseDto dto = new StatementResponseDto();
		dto.setData(dto3);

		dto.setMessage("details displayed successfully");
		dto.setStatusCode(200);
		dto.setStatus("FAILURE");
String accountNumber=null;
		Pageable pageable = PageRequest.of(0, 1);
		Transaction transactions = new Transaction();
		transactions.setAccountNumber("12345");
	
	
		
		List<Transaction> transaction = new ArrayList<Transaction>();
		transaction.add(transactions);
		

		

		
	
Mockito.when(statementRepository.findByAccountNumber(accountNumber, pageable)).thenReturn(transaction);	
StatementResponseDto dto5 = statementService.getStatements(accountNumber);
assertEquals(dto.getStatus(), dto5.getStatus());
		
	}
}
