package com.ing.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mortagage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String mortagageId;
	private String mortagageType;
	private double propertyCost;
	private double deposit;
	private String customerId;
	private double mortagageBalance;

}
