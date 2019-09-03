package com.ing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.dto.BalanceDto;
import com.ing.service.BalanceService;

@RequestMapping("/api")
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class BalanceController {

	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	@Autowired
	BalanceService balanceService;

	/**
	 * Balance Controller 
	 * @author  Sharath
	 */
	@GetMapping("/summary/{customerId}")
	public ResponseEntity<BalanceDto> getBalance(@PathVariable("customerId") String customerId) {
		logger.info("getBalance controller");
		return new ResponseEntity<>(balanceService.getBalance(customerId), HttpStatus.OK);
	}

}
