package com.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ing.dto.LoginReqDto;
import com.ing.dto.LoginResDto;
import com.ing.entity.Customer;
import com.ing.exception.EnterValidCredentials;
import com.ing.exception.UserNotFound;
import com.ing.repository.CustomerRepository;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public LoginResDto userLogin(LoginReqDto loginDto) {
		
		
		Customer details=customerRepository.findByCustomerIdAndPassword(loginDto.getCustomerId(),loginDto.getPassword());
		
		if(null!=details) {
			if (details.getCustomerId().equals(loginDto.getCustomerId()) && details.getPassword().equals(loginDto.getPassword())) {
				LoginResDto loginResDto=new LoginResDto();
				loginResDto.setCustomerId(details.getCustomerId());
				loginResDto.setMessage("Login Success");
				loginResDto.setStatus("SUCCESS");
				loginResDto.setStatusCode(HttpStatus.OK.value());
				return loginResDto;
			}else {
				throw new EnterValidCredentials("Please enter correct username or password");
			}
		}
		else {
			throw new UserNotFound("User does not exists");
		}
	}
}
