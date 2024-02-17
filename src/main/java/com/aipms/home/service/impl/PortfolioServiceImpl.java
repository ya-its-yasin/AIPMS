package com.aipms.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.Portfolio;
import com.aipms.home.repository.PortfolioRepository;
import com.aipms.home.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Autowired
	PortfolioRepository repo;
	
	@Override
	public List<Portfolio> getAllPortfolios() {
		return repo.findAll();
	}

	@Override
	public Portfolio getPortfolio(int id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean addPortfolio(Portfolio port) {
		return repo.save(port) != null;
	}

}
