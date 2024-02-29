package com.aipms.home.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	//private String emailId;
	private double initialRate;//api
	private double finalRate;//api
	private double spread;//api
	private double principal;//i/p from user
	private double n;
	private double interestOne;
	private double time;
	private double interestAmount;
	private double finalreturns;
	@ManyToOne
	private UserProfile frbHolder;
	
}


