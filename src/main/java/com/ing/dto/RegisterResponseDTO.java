package com.ing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDTO {

	private String customerId;
	private String password;
	private String mortgageAcc;
	private String transactionAcc;
	private String message;
	private int statusCode;
	private String status;
}
