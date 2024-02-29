package com.aipms.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Portfolio getPortfolio(int id) {
		
		Portfolio userPortfolio = new Portfolio();
		ResponseEntity<?> response = restTemplate.getForEntity(
				"http://localhost:8090/user/profile/"+id, UserProfile.class);
		UserProfile user = (UserProfile) response.getBody();
		userPortfolio.setUser(user);
		userPortfolio.setFixedDeposists(depositService.getAllFDsOfUser(user));
		userPortfolio.setRecurringDeposits(depositService.getAllRDsOfUser(user));
		userPortfolio.setGolds(goldService.getAllgoldsOfUser(user));
		userPortfolio.setFloatingRateBonds(bondService.getFrbList(id));
		userPortfolio.setSovereignGoldBonds(bondService.getSgbList(id));
		userPortfolio.setMutualFunds(pmfService.getAllPurchasedModel(id));
		
		return userPortfolio;
	}

}
