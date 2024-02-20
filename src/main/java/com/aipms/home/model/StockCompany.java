package com.aipms.home.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class StockCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int companyId;
	@Column(unique=true)
	private String companyName;
	@Column(unique=true)
	private String companySymbol;
	private String niftyType;

}
