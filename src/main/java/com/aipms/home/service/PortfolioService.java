package com.aipms.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aipms.home.model.Portfolio;
import com.aipms.home.model.UserGoal;

@Service
public interface PortfolioService {

	List<Portfolio> getAllPortfolios();

	Portfolio getPortfolio(int id);

	boolean addPortfolio(Portfolio port);

	Portfolio suggestPortfolio(Portfolio goal);

	
}
