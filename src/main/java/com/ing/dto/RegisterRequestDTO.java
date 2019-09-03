package com.ing.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
	
	private String email;
	private String firstName;
	private String lastName;
	private String occupation;
	private String mobile;
	private LocalDate dob;
	private double deposit;
	private double propertyCost;

}
