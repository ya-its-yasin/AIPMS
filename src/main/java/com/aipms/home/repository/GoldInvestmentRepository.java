package com.aipms.home.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.GoldInvestment;

@Repository
public interface GoldInvestmentRepository extends JpaRepository <GoldInvestment ,Integer> {

	//Object save(int gid);

	

}
