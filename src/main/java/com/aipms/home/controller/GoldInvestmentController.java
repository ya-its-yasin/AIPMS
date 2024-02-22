package com.aipms.home.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.GoldInvestment;
import com.aipms.home.service.GoldInvestmentService;
import com.aipms.home.service.impl.GoldInvestmentServiceImpl;

@RestController
@RequestMapping("/gold")
public class GoldInvestmentController {
	@Autowired
	GoldInvestmentService goldservice;

	@GetMapping("/get/{gid}")
	public Optional<GoldInvestment> getProfile(@PathVariable int gid){
		return goldservice.getProfile(gid);
		
	}
	@GetMapping("/getall")
	public List<GoldInvestment> getAllProfiles(){
		return goldservice.getAllProfiles();
	}

	@PostMapping("/buy")
	public boolean buygold(@RequestBody GoldInvestment gold){
		return goldservice.buygold(gold);
	}
}