package com.aipms.home.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.GoldInvestment;
import com.aipms.home.repository.GoldInvestmentRepository;
import com.aipms.home.service.GoldInvestmentService;

@Service
public class GoldInvestmentServiceImpl implements GoldInvestmentService {
	
	@Autowired
	GoldInvestmentRepository goldrepo;

	@Override
	public Optional<GoldInvestment> getProfile(int gid) {
		return goldrepo.findById(gid);
	}

	@Override
	public boolean buygold(GoldInvestment gold) {
		return goldrepo.save(gold) != null;
	}

	@Override
	public List<GoldInvestment> getAllProfiles(int userId) {
		List<Integer> ids = List.of(userId);
		return goldrepo.findAllById(ids);
	}
	

}
