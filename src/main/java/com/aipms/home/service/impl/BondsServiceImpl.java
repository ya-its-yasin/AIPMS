package com.aipms.home.service.impl;

import java.util.List;
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
	FloatingRateBondsRepository frbRepo;
	@Autowired
	SovereignGoldBondsRepository sgbRepo;
	@Autowired
	UserProfileRepository userRepo;
 
    @Override
	public boolean submitSgb(SovereignGoldBonds sgb) {   
    	UserProfile user = userRepo.findById(sgb.getSgbHolder().getUserId()).get();
		user.setWalletBalance(sgb.getSgbHolder().getWalletBalance() - sgb.getIAmount());
		sgb.setSgbHolder(user);
		sgbRepo.save(sgb);
		userRepo.save(user);
		return true;
	}
 
    
	@Override
	public boolean submitFrd(FloatingRateBonds frd) {
 
		UserProfile user = userRepo.findById(frd.getFrbHolder().getUserId()).get();
		user.setWalletBalance(frd.getFrbHolder().getWalletBalance() - frd.getPrincipal());
		frd.setFrbHolder(user);
		System.out.println(user);
		
		System.out.println(frd);
		frbRepo.save(frd);
		userRepo.save(user);
		return true;	
	}

	@Override
	public Optional<FloatingRateBonds> getFrbDetails(int id) {
		return frbRepo.findById(id);		
	}
	@Override
	public List<FloatingRateBonds> getallFrbValues(){
		return frbRepo.findAll();
	}
 
	@Override
	public Optional<SovereignGoldBonds> FetchSgb(int id) {
		return sgbRepo.findById(id);
	}
 
	@Override
	public List<FloatingRateBonds> getFrbList(int userId) {
		//List<Integer> ids = List.of(userId);
		UserProfile user = userRepo.findById(userId).get();
		return frbRepo.findByFrbHolder(user);
	}
	@Override
	public List<SovereignGoldBonds> getSgbList(int userId) {
		//List<Integer> ids = List.of(userId);
		UserProfile user = userRepo.findById(userId).get();
		return sgbRepo.findBySgbHolder(user);
	}
	
	
 
}






