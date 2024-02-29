package com.aipms.home.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.GoldInvestment;
import com.aipms.home.model.UserProfile;
import com.aipms.home.repository.GoldInvestmentRepository;
import com.aipms.home.repository.UserProfileRepository;
import com.aipms.home.service.GoldInvestmentService;

@Service
public class GoldInvestmentServiceImpl implements GoldInvestmentService {
	
	@Autowired
	GoldInvestmentRepository goldrepo;
	
	@Autowired
	UserProfileRepository userRepo;

	@Override
	public Optional<GoldInvestment> getProfile(int gid) {
		return goldrepo.findById(gid);
	}

	@Override
	public boolean buygold(GoldInvestment gold) {
		UserProfile user = userRepo.findById(gold.getGoldHolder().getUserId()).get();
		user.setWalletBalance(gold.getGoldHolder().getWalletBalance() - gold.getPrinciple());
		gold.setGoldHolder(user);
		System.out.println(gold.getGoldHolder().getUserId());
		goldrepo.save(gold);
		userRepo.save(user);
		
		return true;
	}

	@Override
	public List<GoldInvestment> getAllgoldsOfUser(UserProfile user) {
//		List<Integer> ids = List.of(userId);
//		return goldrepo.findAllById(ids);
		return goldrepo.findAllBygoldHolder(user);
	}
	

}
