package com.aipms.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aipms.home.model.MutualFunds;

@Service
public interface MutualFundsService {

	String addInitialInterval(List<MutualFunds> mutualFunds);

	String addLastInterval(List<MutualFunds> mutualFunds);

	String updateLastInterval(List<MutualFunds> mutualFunds);

	String deleteAllRecords();

	List<MutualFunds> getAllRecords();

}
