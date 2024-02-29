package com.aipms.home.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.GoldInvestment;
import com.aipms.home.model.UserProfile;

@Repository
public interface GoldInvestmentRepository extends JpaRepository <GoldInvestment ,Integer> {

	//Object save(int gid);
	List<GoldInvestment> findAllBygoldHolder(UserProfile goldHolder);

	

}
