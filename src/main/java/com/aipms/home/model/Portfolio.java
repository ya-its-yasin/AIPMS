package com.aipms.home.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String name;
	private double mutualFund;
	private double bond;
	private double gold;
	private double deposit;

	private double riskPercent;
	private double returnsPercent;
	private double returnsAmount;
	private double periodYears;
	private double investmentMoney;
	
}
