package com.aipms.home.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGoal {

	private double investmentMoney;
	private double riskPercent;
	private double returnsMoney;
	private double returnPercent;
	private double periodYears;
	
}
