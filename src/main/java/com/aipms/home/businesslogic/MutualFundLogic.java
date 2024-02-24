package com.aipms.home.businesslogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.aipms.home.model.MutualFunds;

@Component
public class MutualFundLogic {
	
	static List<List<MutualFunds>> mutualFundsListofList = new ArrayList<>();
	public List<List<MutualFunds>>  permutatedRecommendation(List<MutualFunds> filteredMutualList,int count, double tAmount,double paymentAmount) {
		permutatedRecommendation1(filteredMutualList,0,0,paymentAmount);
		List<List<MutualFunds>> mutualFundsListofList1 = mutualFundsListofList;
		mutualFundsListofList = new ArrayList<>();
		return mutualFundsListofList1;
		
	}
	public  static void permutatedRecommendation1(List<MutualFunds> filteredMutualList,int count, double tAmount,double paymentAmount) {
		List<MutualFunds> mutualFundsList = new ArrayList<>();
		if(mutualFundsListofList.size()>=15)
			return;
		if(count>=filteredMutualList.size() || filteredMutualList.get(count).getCalculatedAnnualAmount()>paymentAmount)
			return;
		double sum=0;
		for(int i=count;i<filteredMutualList.size();i++) {
			if(sum+filteredMutualList.get(i).getCalculatedAnnualAmount()>paymentAmount) {
				break;
			}
			if(filteredMutualList.get(i).getCalculatedAnnualReturnAmount()>filteredMutualList.get(i).getCalculatedAnnualAmount()) {
				sum+=filteredMutualList.get(i).getCalculatedAnnualAmount();
				mutualFundsList.add(filteredMutualList.get(i));
			}
		}
		if(mutualFundsList!=null && mutualFundsList.size()>=5)
		mutualFundsListofList.add(mutualFundsList);
		count=count+1;
		permutatedRecommendation1(filteredMutualList,count,0,paymentAmount);
		
	}

}
