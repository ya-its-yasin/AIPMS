package com.aipms.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.Portfolio;
import com.aipms.home.model.UserProfile;
import com.aipms.home.service.BondService;
import com.aipms.home.service.DepositService;
import com.aipms.home.service.GoldInvestmentService;
import com.aipms.home.service.PortfolioService;
import com.aipms.home.service.PurchasedMutualFundsService;
import com.aipms.home.service.UserService;

@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Autowired
	UserService userService;
	
	@Autowired
	DepositService depositService;
	
	@Autowired
	GoldInvestmentService goldService;
	
	@Autowired
	BondService bondService;
	
	@Autowired
	PurchasedMutualFundsService pmfService;
	
	@Override
	public Portfolio getPortfolio(int id) {
		
		Portfolio userPortfolio = new Portfolio();
		userPortfolio.setUser((UserProfile)userService.getProfile(id));
		userPortfolio.setFixedDeposists(depositService.getAllFDsOfUser(id));
		userPortfolio.setRecurringDeposits(depositService.getAllRDsOfUser(id));
		userPortfolio.setGolds(goldService.getAllProfiles(id));
		userPortfolio.setFloatingRateBonds(bondService.getFrbList(id));
		userPortfolio.setSovereignGoldBonds(bondService.getSgbList(id));
		userPortfolio.setMutualFunds(pmfService.getAllPurchasedModel(id));
		
		return userPortfolio;
	}

}
