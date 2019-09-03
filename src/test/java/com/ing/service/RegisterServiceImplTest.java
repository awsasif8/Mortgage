package com.ing.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.dto.RegisterRequestDTO;
import com.ing.dto.RegisterResponseDTO;
import com.ing.entity.Account;
import com.ing.entity.Customer;
import com.ing.entity.Mortagage;
import com.ing.exception.MortagageManagementException;
import com.ing.repository.AccountRepository;
import com.ing.repository.CustomerRepository;
import com.ing.repository.MortagageRepository;
import com.ing.util.MailWithOTPService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RegisterServiceImplTest {

	@Mock
	MailWithOTPService api;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	MortagageRepository mortagageRepository;

	@InjectMocks
	RegisterServiceImpl registerServiceImpl;
	
	RegisterRequestDTO registerRequestDTO = null;
	RegisterResponseDTO registerResponseDTO = null;
	Customer customer = null;
	Account account = null;
	Mortagage mortgage = null;
	
	
	@Before
	public void setUp() {
		registerRequestDTO  = new RegisterRequestDTO();
		registerRequestDTO.setDeposit(100);
		registerRequestDTO.setDob(LocalDate.parse("1992-06-06"));
		registerRequestDTO.setEmail("dhanashekara123@gmail.com");
		registerRequestDTO.setFirstName("sdhagsd");
		registerRequestDTO.setLastName("lastname");
		registerRequestDTO.setMobile("1212121212");
		registerRequestDTO.setOccupation("SE");
		registerRequestDTO.setPropertyCost(10000);
		
		registerResponseDTO = new RegisterResponseDTO();
		registerResponseDTO.setCustomerId("as1212");
		registerResponseDTO.setMessage("Registration Successfull");
		registerResponseDTO.setMortgageAcc("MORT1212");
		registerResponseDTO.setPassword("Pass1212");
		registerResponseDTO.setStatus("SUCCESS");
		registerResponseDTO.setStatusCode(201);
		registerResponseDTO.setTransactionAcc("TR11212");
		
		customer = new Customer();
		customer.setCustomerId("123");
		customer.setDob(LocalDate.parse("1992-06-06"));
		customer.setEmail("dhanashekara123@gmail.com");
		customer.setFirstName("abc");
		
		account = new Account();
		account.setAccountNumber("1234");
		account.setAccountType("type");
		account.setBalance(1000);
		account.setCreatedOn(LocalDate.now());
		
		mortgage = new Mortagage();
		
		mortgage.setCustomerId("123");
	}

	@Test
	public void testGenerateOTPandSendMail() {
		
		Mockito.doNothing().when(api).sendEmail("dhanashekara123@gmail.com","ING Transactions","OTP for abc");
		registerServiceImpl.generateOTPandSendMail("dhanashekara123@gmail.com",registerResponseDTO);
	}
	
	@Test(expected = Exception.class)
	public void exceptionTest() throws Exception {
		
		Mockito.doNothing().when(api).sendEmail("dhanashekara123@gmail.com","ING Transactions","OTP for abc");
		registerServiceImpl.generateOTPandSendMail("abc",registerResponseDTO);
		throw new Exception("");
	}
	
	@Test
	public void testRegisterCustomer() {
		
		RegisterResponseDTO actual = registerServiceImpl.registerCustomer(registerRequestDTO);
		when(customerRepository.save(customer)).thenReturn(customer);
		when(accountRepository.save(account)).thenReturn(account);
		when(mortagageRepository.save(mortgage)).thenReturn(mortgage);
		
		assertEquals(registerResponseDTO.getMessage(), actual.getMessage());
	}
	
	@Test(expected = MortagageManagementException.class)
	public void mortagageManagementException()  {
		
		registerRequestDTO.setDob(LocalDate.now());
		RegisterResponseDTO actual = registerServiceImpl.registerCustomer(registerRequestDTO);
		assertEquals("Sorry we are unable to grant you the mortgage at this moment",actual.getMessage());
	}
}
