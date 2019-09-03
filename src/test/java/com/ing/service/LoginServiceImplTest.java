package com.ing.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.dto.LoginReqDto;
import com.ing.dto.LoginResDto;
import com.ing.entity.Customer;
import com.ing.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	
	@InjectMocks
	LoginServiceImpl userLoginService;
	
	@Test
	public void Logintest() {
		LoginReqDto req=new LoginReqDto();
		req.setCustomerId("pradeep.aj28@gmail.com");
		req.setPassword("qwerty");
		LoginResDto res=new LoginResDto();
		res.setMessage("succes");
		res.setStatusCode(200);
		Customer ud=new Customer();
		ud.setEmail("pradeep.aj28@gmail.com");
		ud.setPassword("qwerty");
		ud.setCustomerId("pradeep.aj28@gmail.com");
		Mockito.when(customerRepository.findByCustomerIdAndPassword(req.getCustomerId(),req.getPassword())).thenReturn(ud);
		LoginResDto actualValue = userLoginService.userLogin(req);
		assertEquals(200, actualValue.getStatusCode());
		
	}

}
