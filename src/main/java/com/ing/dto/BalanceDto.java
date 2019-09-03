package com.ing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceDto {

	private String message; 
	private int statusCode;
	private String status;
	private String transactionAccountNumber;
	private Double transactionBalance;
	private String mortagageAccNumber;
	private Double mortagageBalance;
	
}
