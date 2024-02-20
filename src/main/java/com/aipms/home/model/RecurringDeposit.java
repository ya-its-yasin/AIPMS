package com.aipms.home.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecurringDeposit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rdId;
	private double rate;
	private double years;
	private double amount;
	private double interestAmount;
	
}
