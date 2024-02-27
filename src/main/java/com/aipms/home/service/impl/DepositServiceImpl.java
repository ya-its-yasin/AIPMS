package com.aipms.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;
import com.aipms.home.model.UserProfile;
import com.aipms.home.repository.FixedDepositRepository;
import com.aipms.home.repository.RecurringDepositRepository;
import com.aipms.home.repository.UserProfileRepository;
import com.aipms.home.service.DepositService;

@Service
public class DepositServiceImpl implements DepositService {
	
	@Autowired
	FixedDepositRepository fdRepo;
	
	@Autowired
	RecurringDepositRepository rdRepo;
	
	@Autowired
	UserProfileRepository userRepo;
	
	@Override
	public boolean submitFD(FixedDeposit fd) {
		UserProfile user = userRepo.findById(fd.getFdHolder().getUserId()).get();
		user.setWalletBalance(fd.getFdHolder().getWalletBalance() - fd.getDepositAmount());
		fd.setFdHolder(user);
		fdRepo.save(fd);
		userRepo.save(user);
		
		return true;
	}

	@Override
	public boolean submitRD(RecurringDeposit rd) {
		UserProfile user = userRepo.findById(rd.getRdHolder().getUserId()).get();
		user.setWalletBalance(rd.getRdHolder().getWalletBalance() - rd.getRegularMonthlyDepositAmount());
		rd.setRdHolder(user);
		rdRepo.save(rd);
		userRepo.save(user);
		
		return true;
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
