package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aipms.home.model.MutualFunds;
import java.util.List;


public interface MutualFundsRepository extends JpaRepository<MutualFunds, Integer>{
	List<MutualFunds> findByCompanySymbol(String companySymbol);
}
