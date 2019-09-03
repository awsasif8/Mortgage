package com.ing.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private String message;
	private int statusCode;
	private String status;
}