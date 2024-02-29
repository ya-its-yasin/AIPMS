package com.aipms.home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.GoldInvestment;
import com.aipms.home.model.UserProfile;

@Service
public interface GoldInvestmentService {

	Optional<GoldInvestment> getProfile(int gid);

	List<GoldInvestment> getAllgoldsOfUser(UserProfile user);

	boolean buygold(GoldInvestment gold);




}
