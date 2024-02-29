package com.aipms.home.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class GoldInvestment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gid;
	private double principle;
	private double interest;
	private int years;
	private double amount;
	private double goldpurchased;
	private double monthlyrate;
	private double months;
	private double profit;
	
	@ManyToOne
	private UserProfile goldHolder;
//	private int UserId;
}
