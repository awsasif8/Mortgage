package com.ing.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.dto.BalanceDto;
import com.ing.entity.Account;
import com.ing.entity.Mortagage;
import com.ing.exception.MortagageManagementException;
import com.ing.repository.AccountRepository;
import com.ing.repository.MortagageRepository;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

	@InjectMocks
	BalanceServiceImpl balanceService;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	MortagageRepository mortagageRepository;
	
	public Account getAccount()
	{
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setAccountType("Mortagage");
		account.setBalance(20000);
		account.setCustomerId("ING123");
		return account;
	}
	
	public Account getAccount1()
	{
		Account account = new Account();
		return account;
	}
	
	public Mortagage getMortagage()
	{
		Mortagage mortagage = new Mortagage();
		mortagage.setCustomerId("ING123");
		mortagage.setDeposit(100000);
		mortagage.setMortagageBalance(500000);
		mortagage.setMortagageId("MORT12345");
		return mortagage;
	}
	
	public Mortagage getMortagage1()
	{
		Mortagage mortagage = new Mortagage();
		return mortagage;
	}
	
	@Test
	public void getBalanceTest()
	{
		Mockito.when(accountRepository.findBycustomerId(Mockito.anyString())).thenReturn(getAccount());
		Mockito.when(mortagageRepository.findBycustomerId(Mockito.anyString())).thenReturn(getMortagage());
		BalanceDto balanceMessage = balanceService.getBalance(Mockito.anyString());
		Assert.assertEquals("SUCCESS", balanceMessage.getStatus());
	}
	
	@Test(expected = MortagageManagementException.class)
	public void GetBalanceNegative()
	{
		Mockito.when(accountRepository.findBycustomerId(Mockito.anyString())).thenReturn(getAccount1());
		Mockito.when(mortagageRepository.findBycustomerId(Mockito.anyString())).thenReturn(getMortagage());
		BalanceDto balanceMessage = balanceService.getBalance(Mockito.anyString());
		Assert.assertEquals("SUCCESS", balanceMessage.getStatus());
	}
	
	@Test(expected = MortagageManagementException.class)
	public void GetBalanceNegative1()
	{
		Mockito.when(accountRepository.findBycustomerId(Mockito.anyString())).thenReturn(getAccount());
		Mockito.when(mortagageRepository.findBycustomerId(Mockito.anyString())).thenReturn(getMortagage1());
		BalanceDto balanceMessage = balanceService.getBalance(Mockito.anyString());
		Assert.assertEquals("SUCCESS", balanceMessage.getStatus());
	}
	
	
	@Test(expected = MortagageManagementException.class)
	public void GetBalanceNegative2()
	{
		Mockito.when(accountRepository.findBycustomerId(Mockito.anyString())).thenReturn(getAccount1());
		Mockito.when(mortagageRepository.findBycustomerId(Mockito.anyString())).thenReturn(getMortagage1());
		BalanceDto balanceMessage = balanceService.getBalance(Mockito.anyString());
		Assert.assertEquals("SUCCESS", balanceMessage.getStatus());
	}
	
}
