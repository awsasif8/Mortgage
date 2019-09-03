package com.ing.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String accountNumber;
	private double balance;
	private LocalDate createdOn;
	private String customerId;
	private String accountType;
}
