package com.aipms.home.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.GoldInvestment;
import com.aipms.home.service.GoldInvestmentService;

@CrossOrigin(origins = "http://localhost:4200") //port number of angular application
@RestController
@RequestMapping("/gold")
public class GoldInvestmentController {
	
	@Autowired
	GoldInvestmentService goldservice;

	@GetMapping("/get/{gid}")
	public Optional<GoldInvestment> getProfile(@PathVariable int gid){
		return goldservice.getProfile(gid);
	}

	@GetMapping("/getall/{userId}")
	public List<GoldInvestment> getAllProfiles(@PathVariable int userId){
		return goldservice.getAllProfiles(userId);
	}

	@PostMapping("/buy")
	public boolean buygold(@RequestBody GoldInvestment gold){
		return goldservice.buygold(gold);
	}
}