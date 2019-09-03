package com.ing.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ing.dto.RegisterRequestDTO;
import com.ing.dto.RegisterResponseDTO;
import com.ing.service.RegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterControllerTest {

	@Mock
	RegisterService registerSrvice;
	
	@InjectMocks
	RegisterController registerController;
	
	RegisterRequestDTO registerRequestDTO = null;
	RegisterResponseDTO registerResponseDTO = null;
	
	@Before
	public void setUp() {
		registerRequestDTO  = new RegisterRequestDTO();
		registerRequestDTO.setDeposit(100);
		registerRequestDTO.setDob(LocalDate.now());
		registerRequestDTO.setEmail("dhanashekara123@gmail.com");
		registerRequestDTO.setFirstName("sdhagsd");
		registerRequestDTO.setLastName("lastname");
		registerRequestDTO.setMobile("1212121212");
		registerRequestDTO.setOccupation("SE");
		registerRequestDTO.setPropertyCost(10000);
		
		registerResponseDTO = new RegisterResponseDTO();
		registerResponseDTO.setCustomerId("as1212");
		registerResponseDTO.setMessage("Success");
		registerResponseDTO.setMortgageAcc("MORT1212");
		registerResponseDTO.setPassword("Pass1212");
		registerResponseDTO.setStatus("SUCCESS");
		registerResponseDTO.setStatusCode(201);
		registerResponseDTO.setTransactionAcc("TR11212");
	}
	
	@Test
	public void testRegisterCustomer() {
		
		when(registerSrvice.registerCustomer(registerRequestDTO)).thenReturn(registerResponseDTO);
		ResponseEntity<RegisterResponseDTO> actual = registerController.registerCustomer(registerRequestDTO);
		assertEquals(201, actual.getStatusCode().value());
	}

}
