package com.aipms.home.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;
import com.aipms.home.model.UserProfile;
import com.aipms.home.repository.FloatingRateBondsRepository;
import com.aipms.home.repository.SovereignGoldBondsRepository;
import com.aipms.home.repository.UserProfileRepository;
import com.aipms.home.service.BondService;

@Service
public class BondsServiceImpl implements BondService{
	
	@Autowired
	FloatingRateBondsRepository repo;
	
	@Autowired
	SovereignGoldBondsRepository repos;

    @Override
	public boolean submitSgb(SovereignGoldBonds sgb) {
		// TODO Auto-generated method stub
    	
	    repos.save(sgb);
		
		return true;
	}


	@Override
	public boolean submitFrd(FloatingRateBonds frd) {
		// TODO Auto-generated method stub
		 repo.save(frd);
			
		  return true;
		
	}


	@Override
	public Optional<FloatingRateBonds> getFrbDetails(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
		
	}


	@Override
	public Optional<SovereignGoldBonds> FetchSgb(int id) {
		// TODO Auto-generated method stub
		return repos.findById(id);
	}


	
	
}
