package com.aipms.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aipms.home.model.StockCompany;

@Service
public interface StockCompanyService {
	public String addStockCompanies(StockCompany stockCompany);
	public StockCompany getStockCompanies(int companyId);
	public List<StockCompany> getAllStockCompanies();
	String addListOfStockCompanies(List<StockCompany> stockCompanies);
	void deleteAllStockCompanies();
}
