package com.aipms.home.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;

@Service
public interface BondService {
	public boolean submitSgb(SovereignGoldBonds sgb);
 
	public boolean submitFrd(FloatingRateBonds frd);
 
	public Optional<SovereignGoldBonds> FetchSgb(int id);
 
	public Optional<FloatingRateBonds> getFrbDetails(int id);
 
	public List<FloatingRateBonds> getFrbList(int userId);
 
	public List<SovereignGoldBonds> getSgbList(int userId);
 
	List<FloatingRateBonds> getallFrbValues();
 
	
 


}
