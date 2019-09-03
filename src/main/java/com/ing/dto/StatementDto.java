package com.ing.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatementDto {
	private long transactionId;
	private String transactionType;
	private double transactionAmount;
	private String description;
	private LocalDateTime transactionDate;
}
