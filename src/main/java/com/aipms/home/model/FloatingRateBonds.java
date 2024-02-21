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

public class FloatingRateBonds {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int frId;
	private double initialRate;
	private double finalRate;
	private double spread;
	private double interestAmount;
	private double finalreturns;
	
}


