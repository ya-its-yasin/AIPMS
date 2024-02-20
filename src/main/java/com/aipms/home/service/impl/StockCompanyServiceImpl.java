package com.aipms.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.StockCompany;
import com.aipms.home.repository.StockCompanyRepository;
import com.aipms.home.service.StockCompanyService;

@Service
public class StockCompanyServiceImpl implements StockCompanyService {

	@Autowired
	StockCompanyRepository stockRepo;
	@Override
	public String addStockCompanies(StockCompany stockCompany) {
		try {
		stockRepo.save(stockCompany);
		return "Company Details Added Successfully!!!";
		}
		catch(Exception e) {
			return "Company Details Not Added Successfully!!!";
		}
	}
	
	@Override
	public String addListOfStockCompanies(List<StockCompany> stockCompanies) {
		try {
			stockCompanies.forEach(stockCompany->stockRepo.save(stockCompany));
			return "Company Details Added Successfully!!!";
		}
		catch(Exception e) {
			return "Company Details Not Added Successfully!!!";
		}
	}

	@Override
	public StockCompany getStockCompanies(int companyId) {
		try {
			return stockRepo.findById(companyId).get();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<StockCompany> getAllStockCompanies() {
		try {
			return stockRepo.findAll();
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override 
	public void deleteAllStockCompanies() {
		try {
			stockRepo.deleteAll();
		}catch(Exception e) {
		
		}
	}
	
}
