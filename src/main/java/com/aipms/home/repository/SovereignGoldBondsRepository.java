package com.aipms.home.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.FloatingRateBonds;
import com.aipms.home.model.SovereignGoldBonds;
import com.aipms.home.model.UserProfile;

@Repository
public interface SovereignGoldBondsRepository extends JpaRepository<SovereignGoldBonds, Integer> {

	Optional<SovereignGoldBonds> findById(int id);

}
