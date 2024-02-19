package com.aipms.home.service;

import org.springframework.stereotype.Service;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;

@Service
public interface DepositService {

	void submitFD(FixedDeposit fd);

	void submitRD(RecurringDeposit rd);

	
}
