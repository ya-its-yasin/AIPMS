package com.aipms.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;
import com.aipms.home.service.DepositService;

@RestController
@RequestMapping("/deposit")
public class DepositController {
	
	@Autowired
	DepositService service;
	
	@PostMapping("/submit/fd")
	public boolean submitFD(@RequestBody FixedDeposit fd)
	{
		return service.submitFD(fd);
	}
	
	@PostMapping("/submit/rd")
	public boolean submitRD(@RequestBody RecurringDeposit rd)
	{
		return service.submitRD(rd);
	}
}
