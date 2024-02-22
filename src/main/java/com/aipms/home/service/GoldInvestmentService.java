package com.aipms.home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aipms.home.model.GoldInvestment;

@Service
public interface GoldInvestmentService {

	Optional<GoldInvestment> getProfile(int gid);

	List<GoldInvestment> getAllProfiles();

	boolean buygold(GoldInvestment gold);




}
