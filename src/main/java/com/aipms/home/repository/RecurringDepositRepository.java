package com.aipms.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FixedDeposit;
import com.aipms.home.model.RecurringDeposit;
import com.aipms.home.model.UserProfile;

@Repository
public interface RecurringDepositRepository extends JpaRepository<RecurringDeposit, Integer> {

	List<RecurringDeposit> findAllByRdHolder(UserProfile fdHolder);
}
