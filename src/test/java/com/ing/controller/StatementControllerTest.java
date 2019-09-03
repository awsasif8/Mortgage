package com.ing.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ing.dto.StatementResponseDto;
import com.ing.service.StatementService;

@RunWith(MockitoJUnitRunner.class)
public class StatementControllerTest {
	@Mock 
	StatementService statementService;
	@InjectMocks
	StatementController statementController;
	@Test
	public void getStatementsTest()
	{StatementResponseDto dto=new StatementResponseDto();
	dto.setStatusCode(200);
		Mockito.when(statementService.getStatements("12345")).thenReturn(dto);
	ResponseEntity response=statementController.getStatements("12345");
response.getBody();
	assertEquals(dto.getStatusCode(),response.getStatusCodeValue());

}
}
