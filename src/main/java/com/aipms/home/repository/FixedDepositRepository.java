package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.FloatingRateBonds;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, Integer>{
	
}
