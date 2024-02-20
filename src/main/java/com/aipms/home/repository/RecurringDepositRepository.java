package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.RecurringDeposit;

@Repository
public interface RecurringDepositRepository extends JpaRepository<RecurringDeposit, Integer> {

}
