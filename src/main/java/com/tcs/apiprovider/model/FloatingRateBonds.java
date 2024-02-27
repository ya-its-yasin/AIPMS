package com.tcs.apiprovider.model;

import org.springframework.stereotype.Component;

@Component
public class FloatingRateBonds {

	
	private int frId;
	private double initialRate;
	private double finalRate;
	private double spread;
	private double principal;
	private double n;
	private double time;
	private double interestOne;
	private double interestAmount;
	private double finalreturns;
	public FloatingRateBonds() {
		// TODO Auto-generated constructor stub
	}
	
	public double getInitialRate() {
		return initialRate;
	}
	public void setInitialRate(double initialRate) {
		this.initialRate = initialRate;
	}
	public double getFinalRate() {
		return finalRate;
	}
	public void setFinalRate(double finalRate) {
		this.finalRate = finalRate;
	}
	public double getSpread() {
		return spread;
	}
	public void setSpread(double spread) {
		this.spread = spread;
	}
	
	
}


