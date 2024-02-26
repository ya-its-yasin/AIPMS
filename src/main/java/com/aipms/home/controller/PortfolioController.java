package com.aipms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.Portfolio;
import com.aipms.home.service.PortfolioService;

@CrossOrigin(origins = "http://localhost:4200") //port number of angular application
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
	
	@Autowired
	PortfolioService service;
	
	@GetMapping("/{userId}")
	public Portfolio getPortfolio(@PathVariable int userId)
	{
		return service.getPortfolio(userId);
	}
	
	
}
