package com.aipms.home.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aipms.home.model.MutualFunds;
import com.aipms.home.repository.MutualFundsRepository;
import com.aipms.home.service.MutualFundsService;

@Service
public class MutualFundsServiceImpl implements MutualFundsService{
	
	@Autowired
	MutualFundsRepository mutualRepo;
	double[] allocationPercentage = {7.5,8.5,9.8,10,12,15,17.5};
	
	@Override
	public List<MutualFunds> getAllRecords(){
		return mutualRepo.findAll();
	}
	@Override
	public String deleteAllRecords() {
		try {
			mutualRepo.deleteAll();
			return "Records Deleted Successfully!!!";
		}
		catch(Exception e) {
			return "Records Not Deleted Successfully!!!";
		}
	}
	
	static long daysBetweenDates(String date1, String date2)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
		String[] ss = (String.valueOf(date1) + "-"
					+ String.valueOf(date2))
		.split("-");
		String year, month, day;
		year = ss[0];
		month = ss[1];
		day = ss[2];
		LocalDate start = LocalDate.parse(year + " " + month + " " + day , dtf);
		year = ss[3];
		month = ss[4];
		day = ss[5];
		LocalDate end =LocalDate.parse(year + " " + month + " " + day, dtf);
		return ChronoUnit.DAYS.between(start, end);
	}
	
	@Override
	public String addInitialInterval(List<MutualFunds> mutualFunds) {
		try {
			mutualFunds.stream().forEach(stock->mutualRepo.save(stock));
			return "Stock Inital Payments with Details Added Successfully!!!";
		}
		catch(Exception e) {
			return "Stock Inital Payments with Details Not Added Successfully!!!";
		}
	}
	
	@Override
	public String addLastInterval(List<MutualFunds> mutualFunds) {
		try {
			mutualFunds.stream().forEach(stock->{
				List<MutualFunds> mf = mutualRepo.findByCompanySymbol(stock.getCompanySymbol());
				if(mf!=null && mf.get(0)!=null) {
					MutualFunds tempMF = mf.get(0);

					SecureRandom sr= new SecureRandom();
					int rIndex = sr.nextInt(allocationPercentage.length);
					tempMF.setAllocationPercentageFromMoney(allocationPercentage[rIndex]);
					tempMF.setLastActionAmount(stock.getLastActionAmount());
					tempMF.setLastActionDate(stock.getLastActionDate());
					
					
					
					int interval;
					
						interval = (int)daysBetweenDates(tempMF.getInitialActionDate(), tempMF.getLastActionDate());
						tempMF.setInterval(interval);
					
					
					double expectedReturn = ((tempMF.getLastActionAmount()-tempMF.getInitialActionAmount())/tempMF.getInitialActionAmount())*100;
					double annualizedReturn = Math.pow((1+(expectedReturn/100)),(365/tempMF.getInterval()));
					
					tempMF.setReturnsAnnualAmount(annualizedReturn*tempMF.getInitialActionAmount());
					tempMF.setReturnsPercentage(annualizedReturn);
					tempMF.setCalculatedAnnualAmount((tempMF.getLastActionAmount()*tempMF.getAllocationPercentageFromMoney())/100);
					tempMF.setCalculatedAnnualReturnAmount((tempMF.getReturnsAnnualAmount()*tempMF.getAllocationPercentageFromMoney())/100);
					mutualRepo.save(tempMF);
				}
			});
			return "Calculated Amount and Data Added Successfully!!!";
		}
		catch(Exception e) {
			return "Data not added Successfully!!!";
		}
	}
	
	@Override
	public String updateLastInterval(List<MutualFunds> mutualFunds) {
		try {
			mutualFunds.stream().forEach(stock->{
				List<MutualFunds> mf = mutualRepo.findByCompanySymbol(stock.getCompanySymbol());
				if(mf!=null && mf.get(0)!=null) {
					MutualFunds tempMF = mf.get(0);
					tempMF.setInitialActionDate(tempMF.getLastActionDate());
					tempMF.setInitialActionAmount(tempMF.getLastActionAmount());
					
					tempMF.setLastActionAmount(stock.getLastActionAmount());
					tempMF.setLastActionDate(stock.getLastActionDate());
					
					
					int interval;
					interval = (int)daysBetweenDates(tempMF.getInitialActionDate(), tempMF.getLastActionDate());
					tempMF.setInterval(interval);
					
					
					double expectedReturn = ((tempMF.getLastActionAmount()-tempMF.getInitialActionAmount())/tempMF.getInitialActionAmount())*100;
					double annualizedReturn = Math.pow((1+(expectedReturn/100)),(365/tempMF.getInterval()));
					
					tempMF.setReturnsAnnualAmount(annualizedReturn*tempMF.getInitialActionAmount());
					tempMF.setReturnsPercentage(annualizedReturn);
					tempMF.setCalculatedAnnualAmount((tempMF.getLastActionAmount()*tempMF.getAllocationPercentageFromMoney())/100);
					tempMF.setCalculatedAnnualReturnAmount((tempMF.getReturnsAnnualAmount()*tempMF.getAllocationPercentageFromMoney())/100);
					
					mutualRepo.save(tempMF);
					
				}
			});
			return "Calculated Amount and Data Updated Successfully!!!";
		}
		catch(Exception e) {
			return "Data not added Successfully!!!";
		}
	}
}
