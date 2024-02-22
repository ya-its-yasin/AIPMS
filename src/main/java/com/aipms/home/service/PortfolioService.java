package com.aipms.home.service;

import org.springframework.stereotype.Service;

import com.aipms.home.model.Portfolio;

@Service
public interface PortfolioService {

	Portfolio getPortfolio(int id);

}
