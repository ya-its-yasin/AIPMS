package com.aipms.home.model;

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
public class PurchasedMutualFunds {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private int userId;
	private String purchaseId;
	private String companySymbol;
	private String companyName;
	private double boughtPercentage;
	private double boughtAmount;
	private String activeStatus;
	private double currentReturnAmount;
}
