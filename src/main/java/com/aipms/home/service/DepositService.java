package com.aipms.home.service;

import org.springframework.stereotype.Service;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;

@Service
public interface DepositService {

	boolean submitFD(FixedDeposit fd);

	boolean submitRD(RecurringDeposit rd);

	
}
