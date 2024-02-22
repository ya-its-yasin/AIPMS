package com.aipms.home.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MutualFunds {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int companyId;
	
	@Column(unique=true)
	private String companyName;
	
	@Column(unique=true)
	private String companySymbol;
	private String riskCategory;
	private String capsCategory;
	private String initialActionDate;
	private double initialActionAmount;
	private String lastActionDate;
	private double lastActionAmount;
	@Column(name = "intervalTimePeriod")
	private int interval;
	private double calculatedAnnualAmount;
	private double calculatedAnnualReturnAmount;
	private double returnsPercentage;
	private double returnsAnnualAmount;
	private double allocationPercentageFromMoney;
	
}
