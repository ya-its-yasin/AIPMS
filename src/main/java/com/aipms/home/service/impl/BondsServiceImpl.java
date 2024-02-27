package com.aipms.home.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;
import com.aipms.home.repository.FloatingRateBondsRepository;
import com.aipms.home.repository.SovereignGoldBondsRepository;
import com.aipms.home.service.BondService;

@Service
public class BondsServiceImpl implements BondService{
	
	@Autowired
	FloatingRateBondsRepository frbRepo;
	
	@Autowired
	SovereignGoldBondsRepository sgbRepo;

    @Override
	public boolean submitSgb(SovereignGoldBonds sgb) {    	
	    sgbRepo.save(sgb);
		return true;
	}

	@Override
	public boolean submitFrd(FloatingRateBonds frd) {
		 frbRepo.save(frd);		
		  return true;		
	}

	@Override
	public Optional<FloatingRateBonds> getFrbDetails(int id) {
		return frbRepo.findById(id);		
	}

	@Override
	public Optional<SovereignGoldBonds> FetchSgb(int id) {
		return sgbRepo.findById(id);
	}

	@Override
	public List<FloatingRateBonds> getFrbList(int userId) {
		List<Integer> ids = List.of(userId);
		return frbRepo.findAllById(ids);
	}
	
	@Override
	public List<SovereignGoldBonds> getSgbList(int userId) {
		List<Integer> ids = List.of(userId);
		return sgbRepo.findAllById(ids);
	}

}
