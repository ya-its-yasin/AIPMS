package com.aipms.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.UserProfile;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, Integer>{
	
	List<FixedDeposit> findAllByFdHolder(UserProfile fdHolder);
	
}
