package com.aipms.home.service;
import org.springframework.stereotype.Service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;
import com.aipms.home.model.UserProfile;

@Service
public interface BondService {
	public boolean submitSgb(SovereignGoldBonds sgb);

	public boolean submitFrd(FloatingRateBonds frd);

	public Optional<SovereignGoldBonds> FetchSgb(int id);

	public Optional<FloatingRateBonds> getFrbDetails(int id);

}
