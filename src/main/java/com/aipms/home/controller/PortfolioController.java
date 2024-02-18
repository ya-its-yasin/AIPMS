package com.aipms.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.Portfolio;
import com.aipms.home.model.UserGoal;
import com.aipms.home.service.PortfolioService;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
	
	@Autowired
	PortfolioService service;

	@GetMapping("/all")
	public List<Portfolio> getAllPortfolios()
	{
		return service.getAllPortfolios(); 
	}
	
	@GetMapping("/{id}")
	public Portfolio getPortfolio(@PathVariable int id)
	{
		return service.getPortfolio(id);
	}
	
	@PostMapping("/add")
	public boolean addPortfolio(@RequestBody Portfolio port)
	{
		System.out.println(port.getName());
		return service.addPortfolio(port); 
	}
	
	@GetMapping("/suggest")
	public Portfolio suggestPortfolio(@RequestBody Portfolio goal)
	{
		return service.suggestPortfolio(goal);
	}
	
	
}
