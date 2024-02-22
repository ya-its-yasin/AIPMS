package com.aipms.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aipms.home.model.MutualFunds;


public interface MutualFundsRepository extends JpaRepository<MutualFunds, Integer>{
	List<MutualFunds> findByCompanySymbol(String companySymbol);
}
