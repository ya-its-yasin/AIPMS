package com.aipms.home.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.aipms.home.service.MutualFundsService;
import com.aipms.home.service.PurchasedMutualFundsService;
import com.aipms.home.service.StockCompanyService;
import com.aipms.home.service.UserService;

@RestController
@RequestMapping("/stocks")
@CrossOrigin("http://localhost:4200/")
public class StockController {
	
	@Autowired
	StockCompanyService stockCService;
	
	@Autowired
	MutualFundLogic mfLogic;
	
	@Autowired
	MutualFundsService mutualFService;
	
	@Autowired
	PurchasedMutualFundsService pMFService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/purchasemutualfunds")
	public String purchaseMutualFunds(@RequestBody List<PurchasedMutualFunds> purchasedMutualFunds) {
		try {
			List<Double> boughtAmounts=new ArrayList<>();
			List<PurchasedMutualFunds> pmfList = new ArrayList<>();
			double totalBoughtAmount=0;
			List<Integer> userids = new ArrayList<>();
			purchasedMutualFunds.stream().forEach(mf->{
				mf.setActiveStatus("ACTIVE");
				mf.setCurrentReturnAmount(mf.getBoughtAmount())	;
				boughtAmounts.add(mf.getBoughtAmount());
				userids.add(mf.getUserId());
				pmfList.add(mf);
			});
			for(Double bAmount:boughtAmounts) {
				totalBoughtAmount+=bAmount;
			}
			String message = pMFService.UpdatePurchasedMutualFundsData(pmfList);
			if(message.toLowerCase().contains("not"))
				return "Not Successful";
			else {
				userService.updateDebitWalletAmount(totalBoughtAmount, userids.get(0));
				return "Purchase Successful";
			}
			
		}
		catch(Exception e) {
			return "Not Successful";
		}
	}
	
	@PostMapping("/getrespectivemutualfunds")
	public List<List<PurchasedMutualFunds>> getRespectiveMutualFunds(@RequestBody UserProfile userprofile){
		List<PurchasedMutualFunds> purchaseMutualFundsList = pMFService.getAllPurchasedModel(userprofile.getUserId());
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
			List<Double> boughtAmounts=new ArrayList<>();
			double totalBoughtAmount=0;
			List<Integer> userids = new ArrayList<>();
			List<PurchasedMutualFunds> pmfList = new ArrayList<>();
			purchasedMutualFunds.stream().forEach(mf->{
				mf.setActiveStatus("INACTIVE");
				boughtAmounts.add(mf.getBoughtAmount());
				userids.add(mf.getUserId());
				pmfList.add(mf);
				pmfList.add(mf);
			});
			for(Double bAmount:boughtAmounts) {
				totalBoughtAmount+=bAmount;
			}
			String message = pMFService.UpdatePurchasedMutualFundsData(pmfList);
			if(message.toLowerCase().contains("not"))
				return "Not Successful";
			else {
				userService.updateCreditWalletAmount(totalBoughtAmount, userids.get(0));
				return "Withdrawn Successful";
			}
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
	
	@GetMapping("/listcompany/{companyId}")
	public StockCompany listCompanyById(@RequestParam int companyId) {
		return stockCService.getStockCompanies(companyId);
	}

}
