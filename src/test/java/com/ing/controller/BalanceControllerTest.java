package com.ing.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.ing.dto.BalanceDto;
import com.ing.service.BalanceService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BalanceControllerTest {

	
	private MockMvc mockMvc;
	
	@InjectMocks
	BalanceController balanceController;
	
	@Mock
	BalanceService balanceService;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(balanceController).build();
    }
	
	public BalanceDto getBalanceDto()
	{
		BalanceDto balance = new BalanceDto();
		balance.setMortagageAccNumber("ING123");
		balance.setMessage("Got the balance");
		balance.setMortagageBalance((double) 20000);
		balance.setStatus("SUCCESS");
		balance.setStatusCode(200);
		balance.setTransactionAccountNumber("ING12345");
		balance.setTransactionBalance((double) 30000);
		return balance;
	}
	
	@Test
	public void getBalanceController()
	{
		ResponseEntity<BalanceDto> expResult = new ResponseEntity<>(getBalanceDto(), HttpStatus.OK);
		when(balanceService.getBalance(Mockito.anyString())).thenReturn(getBalanceDto());
		ResponseEntity<BalanceDto> actResult = balanceController.getBalance(Mockito.anyString());
		assertEquals(expResult.getStatusCode(), actResult.getStatusCode());
	}
	
}
