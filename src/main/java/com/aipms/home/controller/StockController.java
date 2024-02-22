package com.aipms.home.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.businesslogic.MutualFundLogic;
import com.aipms.home.model.FilterMutualFundOptions;
import com.aipms.home.model.MutualFunds;
import com.aipms.home.model.PurchasedMutualFunds;
import com.aipms.home.model.StockCompany;
import com.aipms.home.model.UserProfile;
import com.aipms.home.service.impl.MutualFundsServiceImpl;
import com.aipms.home.service.impl.PurchasedMutualFundsServiceImpl;
import com.aipms.home.service.impl.StockCompanyServiceImpl;
import com.aipms.home.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	StockCompanyServiceImpl stockCService;
	
	@Autowired
	MutualFundLogic mfLogic;
	
	@Autowired
	MutualFundsServiceImpl mutualFService;
	
	@Autowired
	PurchasedMutualFundsServiceImpl pMFServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@PostMapping("/purchasemutualfunds")
	public String purchaseMutualFunds(@RequestBody List<PurchasedMutualFunds> purchasedMutualFunds) {
		try {
			List<PurchasedMutualFunds> pmfList = new ArrayList<>();
			purchasedMutualFunds.stream().forEach(mf->{
				mf.setActiveStatus("ACTIVE");
				pmfList.add(mf);
			});
			String message = pMFServiceImpl.UpdatePurchasedMutualFundsData(pmfList);
			if(message.toLowerCase().contains("not"))
				return "Not Successful";
			else
				return "Successful";
		}
		catch(Exception e) {
			return "Not Successful";
		}
	}
	
	@PostMapping("/getrespectivemutualfunds")
	public List<List<PurchasedMutualFunds>> getRespectiveMutualFunds(@RequestBody UserProfile userprofile){
		List<PurchasedMutualFunds> purchaseMutualFundsList = pMFServiceImpl.getAllPurchasedModel(userprofile.getUserId());
		List<List<PurchasedMutualFunds>> ListofListMF = new ArrayList<>();
		if(purchaseMutualFundsList==null)
			return null;
		String t=null;
		List<PurchasedMutualFunds> mfList = new ArrayList<>();
		for(PurchasedMutualFunds pmf:purchaseMutualFundsList) {
			if(t==null) {
				t=pmf.getPurchaseId();
				mfList.add(pmf);
			}
			else if(t.equals(pmf.getPurchaseId())) {
				mfList.add(pmf);
			}
			else {
				ListofListMF.add(mfList);
				t=pmf.getPurchaseId();
				mfList = new ArrayList<>();
				mfList.add(pmf);
			}
		}
		ListofListMF.add(mfList);
		return ListofListMF;
		
		
	}
	
	@PostMapping("/withdrawmutualfunds")
	public String withdrawMutualFunds(@RequestBody List<PurchasedMutualFunds> purchasedMutualFunds) {
		try {
			List<PurchasedMutualFunds> pmfList = new ArrayList<>();
			purchasedMutualFunds.stream().forEach(mf->{
				mf.setActiveStatus("INACTIVE");
				pmfList.add(mf);
			});
			String message = pMFServiceImpl.UpdatePurchasedMutualFundsData(pmfList);
			if(message.toLowerCase().contains("not"))
				return "Not Successful";
			else
				return "Successful";
		}
		catch(Exception e) {
			return "Not Successful";
		}
	}
	
	@PostMapping("/mutualfunds")
	public List<List<MutualFunds>> getRecommendedMutualFunds(@RequestBody FilterMutualFundOptions filterOptions) {
		List<MutualFunds> mutualFundsList = mutualFService.getAllRecords();
		List<MutualFunds> filteredMutualList = mutualFundsList.stream().filter(mutualF->
		mutualF.getCapsCategory().equalsIgnoreCase(filterOptions.getCapsCategory()) &&
		mutualF.getRiskCategory().equalsIgnoreCase(filterOptions.getRiskCategory()) &&
		mutualF.getLastActionAmount()*(mutualF.getAllocationPercentageFromMoney()/100)<=
		filterOptions.getPaymentAmount()).
		sorted(Comparator.comparingDouble(MutualFunds::getCalculatedAnnualReturnAmount).reversed()).collect(Collectors.toList());
		
		return mfLogic.permutatedRecommendation(filteredMutualList,0,0,filterOptions.getPaymentAmount());
	}
	
	
		
	

	@PostMapping("/addcompanies")
	public String addCompaniesInList(@RequestBody List<StockCompany> stockCompanies) {
		
		stockCompanies.stream().forEach(stockCompany ->{
			stockCService.addStockCompanies(stockCompany);
		});
		return "Stock Companies Details Added Successfully!!!";
	}
	
	@GetMapping("/listallcompanies")
	public List<StockCompany> listCompanies() {
		return stockCService.getAllStockCompanies();
	}
	
	@GetMapping("/listcompany/{id}")
	public StockCompany listCompanyById(@RequestParam int companyId) {
		return stockCService.getStockCompanies(companyId);
	}

}
