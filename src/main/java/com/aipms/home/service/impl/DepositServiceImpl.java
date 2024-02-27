package com.aipms.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;
import com.aipms.home.repository.FixedDepositRepository;
import com.aipms.home.repository.RecurringDepositRepository;
import com.aipms.home.service.DepositService;

@Service
public class DepositServiceImpl implements DepositService {
	
	@Autowired
	FixedDepositRepository fdRepo;
	
	@Autowired
	RecurringDepositRepository rdRepo;
	
	@Override
	public boolean submitFD(FixedDeposit fd) {
		fd.setFdHolder(null);
		return fdRepo.save(fd) != null;
	}

	@Override
	public boolean submitRD(RecurringDeposit rd) {
		return rdRepo.save(rd) != null;
	}

	@Override
	public List<FixedDeposit> getAllFDsOfUser(int userId) {
		List<Integer> ids = List.of(userId);
		return fdRepo.findAllById(ids);
	}

	@Override
	public List<RecurringDeposit> getAllRDsOfUser(int userId) {
		List<Integer> ids = List.of(userId);
		return rdRepo.findAllById(ids);
	}

}
