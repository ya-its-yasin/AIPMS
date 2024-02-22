package com.aipms.home.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterMutualFundOptions {
	@JsonProperty("capsCategory")
	private String capsCategory;
	@JsonProperty("riskCategory")
	private String riskCategory;
	@JsonProperty("paymentAmount")
	private double paymentAmount;
}
