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
import com.aipms.home.model.StockCompany;
import com.aipms.home.service.impl.MutualFundsServiceImpl;
import com.aipms.home.service.impl.StockCompanyServiceImpl;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	StockCompanyServiceImpl stockCService;
	
	@Autowired
	MutualFundLogic mfLogic;
	
	@Autowired
	MutualFundsServiceImpl mutualFService;
	
	@GetMapping
	public String apiCheck()
	{
		return "I'm working";
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
