package com.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.service.StatementService;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = {"*","/"}, origins = {"*","/"})
public class StatementController {
	
	@Autowired
	StatementService statementService;
	@GetMapping("/statements/{accountNumber}")
	
	public ResponseEntity getStatements(@PathVariable String accountNumber)
	{
		
		return new ResponseEntity<>(statementService.getStatements(accountNumber),HttpStatus.OK);
	}

}

