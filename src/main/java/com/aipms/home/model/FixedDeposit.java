package com.aipms.home.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class FixedDeposit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int fdId;
	private double depositAmount;
	private double rate;
	private double tenureYears;
	private String interestPayoutType;
	private double interestPayoutAmount;
	private double maturityAmount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date StartDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date EndDate;
	
	@ManyToOne
	private UserProfile fdHolder;
	
}
