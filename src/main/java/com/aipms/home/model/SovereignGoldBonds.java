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

public class SovereignGoldBonds{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int gId;
	private String emailId;
	private double iAmount;//(investment)
	private double gprice;//(current gold price)
	private double interestRate;
	private int n;//5
	private double totalInterest;
    private double maturityValue;
    private double facevalue;
    @ManyToOne
    private UserProfile sgbHolder;
}
