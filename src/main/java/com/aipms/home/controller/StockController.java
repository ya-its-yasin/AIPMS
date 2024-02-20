package com.aipms.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.StockCompany;
import com.aipms.home.service.impl.StockCompanyServiceImpl;

@RestController("/stocks")
public class StockController {
	
	@Autowired
	StockCompanyServiceImpl stockCService;
	
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
