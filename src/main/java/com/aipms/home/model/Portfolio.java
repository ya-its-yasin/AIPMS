package com.aipms.home.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

	private UserProfile user;
	private List<FixedDeposit> fixedDeposists;
	private List<RecurringDeposit> recurringDeposits;
	private List<GoldInvestment> golds;
	private List<FloatingRateBonds> floatingRateBonds;
	private List<SovereignGoldBonds> sovereignGoldBonds;
	private List<PurchasedMutualFunds> mutualFunds;
	
}
