package com.aipms.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.MutualFunds;
import com.aipms.home.model.PurchasedMutualFunds;

@Repository
public interface PurchasedMutualFundsRepository extends JpaRepository<PurchasedMutualFunds, Integer>{

	@Query("SELECT e from PurchasedMutualFunds e where e.userId = :userId and e.activeStatus = :activeStatus order by e.purchaseId")
	public List<PurchasedMutualFunds> getUserActiveMutualFunds(@Param("userId") int userId, @Param("activeStatus") String activeStatus);
	
}
