package com.aipms.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aipms.home.model.StockCompany;

@Repository
public interface StockCompanyRepository extends JpaRepository<StockCompany, Integer>{

}
