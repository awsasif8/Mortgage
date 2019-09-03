package com.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.dto.RegisterRequestDTO;
import com.ing.dto.RegisterResponseDTO;
import com.ing.service.RegisterService;

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class RegisterController {

	@Autowired
	RegisterService registerSrvice;

	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDTO> registerCustomer(@RequestBody RegisterRequestDTO requestDTO) {

		return new ResponseEntity<>(registerSrvice.registerCustomer(requestDTO), HttpStatus.CREATED);
	}
}
