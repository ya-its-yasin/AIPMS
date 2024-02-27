package com.aipms.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.PurchasedMutualFunds;
import com.aipms.home.repository.PurchasedMutualFundsRepository;
import com.aipms.home.service.PurchasedMutualFundsService;

@Service
public class PurchasedMutualFundsServiceImpl implements PurchasedMutualFundsService{
	@Autowired
	PurchasedMutualFundsRepository pMFRepo;
	
	@Override
	public String UpdatePurchasedMutualFundsData(List<PurchasedMutualFunds> purchasedMutualFundsList) {
		try {
			pMFRepo.saveAll(purchasedMutualFundsList);
			return "Purchased Mutual Funds Added Successfully!!!";
		}
		catch(Exception e) {
			return "Purchased Mutual Funds Not Added Successfully!!!";
		}
	}
	
	@Override
	public List<PurchasedMutualFunds> getAllPurchasedModel(int userId){
		try {
			return pMFRepo.getUserActiveMutualFunds(userId, "ACTIVE");
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public int updateReturnAmount(double returnAmount, String companySymbol) {
		try {
			return pMFRepo.updateReturnAmount(returnAmount, companySymbol, "ACTIVE");
		}
		catch(Exception e) {
			return 0;
		}
	}

}
