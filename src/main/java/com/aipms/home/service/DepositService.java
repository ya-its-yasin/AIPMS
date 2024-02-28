package com.aipms.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;
import com.aipms.home.model.UserProfile;

@Service
public interface DepositService {

	boolean submitFD(FixedDeposit fd);

	boolean submitRD(RecurringDeposit rd);

	List<FixedDeposit> getAllFDsOfUser(UserProfile user);

	List<RecurringDeposit> getAllRDsOfUser(UserProfile user);

	
}
