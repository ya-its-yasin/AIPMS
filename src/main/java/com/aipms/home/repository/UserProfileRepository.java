package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aipms.home.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

	UserProfile findByEmailId(String emailId);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserProfile e SET e.walletBalance = e.walletBalance - :walletBalance where e.userId = :userId")
	public int updateDebitWalletAmount(@Param("walletBalance") double walletBalance, @Param("userId") int userId);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE UserProfile e SET e.walletBalance = e.walletBalance + :walletBalance where e.userId = :userId")
	public int updateCreditWalletAmount(@Param("walletBalance") double walletBalance, @Param("userId") int userId);
	

}
