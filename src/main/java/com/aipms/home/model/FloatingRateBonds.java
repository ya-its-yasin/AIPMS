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
	private String emailId;
	private double initialRate;//api
	private double finalRate;//api
	private double spread;//api
	private double principle;//i/p from user
	private double n;//i/p -->user (no.of.years-->max 7 years)
	//private double interestAmount;
	//private double finalreturns;
	
}


