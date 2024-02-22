package com.aipms.home.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;
import com.aipms.home.service.BondService;

@RestController
@RequestMapping("/bonds")
public class BondController{
		
	@Autowired
	BondService service;
	
	@PostMapping("/submit/frd")
	public boolean submitFrd(@RequestBody FloatingRateBonds frd)
	{
		service.submitFrd(frd);
		return true;
	}
	
	@PostMapping("/submit/sgb")
	public boolean submitSgb(@RequestBody SovereignGoldBonds sgb)
	{
		service.submitSgb(sgb);
		return true;
	}
	

	@GetMapping("/frb-profile/{id}")
	public Optional<FloatingRateBonds> getFrbDetails(@PathVariable int id)
	{
		return service.getFrbDetails(id);
	
     }
	
	@GetMapping("/sgb-profile/{id}")
	public Optional<SovereignGoldBonds> FetchSgb(@PathVariable int id)
	{
		return service.FetchSgb(id);
	
	}
	
	@GetMapping("/frb-all/{userId}")
	public List<FloatingRateBonds> getFrbList(@PathVariable int userId)
	{
		return service.getFrbList(userId);
	
     }
	
	@GetMapping("/sgb-all/{userId}")
	public List<SovereignGoldBonds> getSgbList(@PathVariable int userId)
	{
		return service.getSgbList(userId);
	
	}
}


	


