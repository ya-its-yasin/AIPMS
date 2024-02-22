package com.aipms.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aipms.home.model.PurchasedMutualFunds;

@Service
public interface PurchasedMutualFundsService {

	String UpdatePurchasedMutualFundsData(List<PurchasedMutualFunds> purchasedMutualFundsList);

	List<PurchasedMutualFunds> getAllPurchasedModel(int userId);

}
