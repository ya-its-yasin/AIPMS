package com.aipms.home.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aipms.home.model.MutualFunds;
import com.aipms.home.model.StockCompany;
import com.aipms.home.service.impl.MutualFundsServiceImpl;
import com.aipms.home.service.impl.StockCompanyServiceImpl;

@Component
public class StockSchedulers {
	@Autowired
	StockCompanyServiceImpl stockCompanyServiceImpl;
	
	@Autowired
	MutualFundsServiceImpl mutualFundsServiceImpl;
	
	@Value("${mutualfund.initial.timestamp}")
	String initialDate;

	@Value("${mutualfund.last.timestamp}")
	String lastDate;
	
	@Scheduled(cron="${stock_mutualfund_add_last_cron}")
	public void addLastMutualFund() {
		System.out.println("Mutual Funds Last Amount Batch Started!!!");
		List<MutualFunds> mutualFunds = mutualFundsServiceImpl.getAllRecords();
		List<MutualFunds> mutualFundsList = new ArrayList<>();
		for(MutualFunds mutualFund:mutualFunds) {
			String urlString ="https://query1.finance.yahoo.com/v7/finance/download/"+mutualFund.getCompanySymbol()+".NS?period1="+lastDate+"&period2="+lastDate+"&interval=1d&events=history";
			try {
				URL url=new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while((inputLine=in.readLine())!=null) {
					response.append(inputLine).append("\n");
				}
				String stockPrice = response.toString().split("\n")[1].split(",")[4];
				mutualFund.setLastActionAmount(Double.parseDouble(stockPrice));
				mutualFund.setLastActionDate("2024-02-01");
				mutualFundsList.add(mutualFund);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mutualFundsServiceImpl.addLastInterval(mutualFundsList);
		System.out.println("Mutual Funds Last Amount Batch Completed!!!");
		
		
	}
	
	@Scheduled(cron="${stock_mutualfund_add_inital_cron}")
	public void addInitialMutualFund() {
		mutualFundsServiceImpl.deleteAllRecords();
		System.out.println("Mutual Funds Initial Amount Batch Started!!!");
		List<StockCompany> stockCompanies = stockCompanyServiceImpl.getAllStockCompanies();
		List<MutualFunds> mutualFundsList = new ArrayList<>();
		for(StockCompany stockCompany:stockCompanies) {
			String urlString ="https://query1.finance.yahoo.com/v7/finance/download/"+stockCompany.getCompanySymbol()+".NS?period1="+initialDate+"&period2="+initialDate+"&interval=1d&events=history";
			try {
				URL url=new URL(urlString);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while((inputLine=in.readLine())!=null) {
					response.append(inputLine).append("\n");
				}
				String stockPrice = response.toString().split("\n")[1].split(",")[4];
				MutualFunds mutualFund = new MutualFunds();
				mutualFund.setInitialActionAmount(Double.parseDouble(stockPrice));
				mutualFund.setCompanySymbol(stockCompany.getCompanySymbol());
				mutualFund.setCompanyName(stockCompany.getCompanyName());
				if(stockCompany.getNiftyType().toLowerCase().contains("mid")) {
					mutualFund.setRiskCategory("Medium");
					mutualFund.setCapsCategory("Mid");
				}
				else if(stockCompany.getNiftyType().toLowerCase().contains("small")) {
					mutualFund.setRiskCategory("High");
					mutualFund.setCapsCategory("Small");
				}
				else  {
					mutualFund.setRiskCategory("Low");
					mutualFund.setCapsCategory("Large");
				}
				mutualFund.setInitialActionDate("2023-10-31");
				mutualFundsList.add(mutualFund);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mutualFundsServiceImpl.addInitialInterval(mutualFundsList);
		System.out.println("Mutual Funds Initial Amount Batch Completed!!!");
		
		
	}
	
	@Scheduled(cron="${stock_company_cron}")
	public void scheduledAddCompanies() {
		System.out.println("Stock Company Batch Started...");
		stockCompanyServiceImpl.deleteAllStockCompanies();
		List<StockCompany> listOfStockCompanies = new ArrayList<>();
		String niftyUrl1="http://localhost:8085/apiprovider/stocknifty50details";
		String niftyUrl2="http://localhost:8085/apiprovider/stockniftynext50details";
		String niftyUrl3="http://localhost:8085/apiprovider/stockniftymid100details";
		String niftyUrl4="http://localhost:8085/apiprovider/stockniftysmall100details";
		HttpClient httpClient = HttpClients.createDefault();
		try {
		HttpGet httpGet = new HttpGet(niftyUrl1);
		HttpResponse response =httpClient.execute(httpGet);
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder result = new StringBuilder();
		String line;
		while((line=reader.readLine())!=null) {
			result.append(line);
		}
		JSONObject jsonResponse = new JSONObject(result.toString());
		JSONArray dataArray = jsonResponse.getJSONArray("data");
		
		for(int i=0;i<dataArray.length();i++) {
			JSONObject dataObject =dataArray.getJSONObject(i);
			if(!dataObject.has("meta"))
				continue;
			JSONObject metaObject = dataObject.getJSONObject("meta");
			
			if(!metaObject.has("companyName")||!metaObject.has("symbol"))
				continue;
			StockCompany stC = new StockCompany();
			stC.setCompanyName(metaObject.getString("companyName"));
			stC.setCompanySymbol(metaObject.getString("symbol"));
			stC.setNiftyType("NIFTY50");
			listOfStockCompanies.add(stC);
		}
		
		httpGet = new HttpGet(niftyUrl2);
		response =httpClient.execute(httpGet);
		reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		result = new StringBuilder();
		line="";
		while((line=reader.readLine())!=null) {
			result.append(line);
		}
		jsonResponse = new JSONObject(result.toString());
		dataArray = jsonResponse.getJSONArray("data");
		
		for(int i=0;i<dataArray.length();i++) {
			JSONObject dataObject =dataArray.getJSONObject(i);
			if(!dataObject.has("meta"))
				continue;
			JSONObject metaObject = dataObject.getJSONObject("meta");
			
			if(!metaObject.has("companyName")||!metaObject.has("symbol"))
				continue;
			StockCompany stC = new StockCompany();
			stC.setCompanyName(metaObject.getString("companyName"));
			stC.setCompanySymbol(metaObject.getString("symbol"));
			stC.setNiftyType("NIFTYNEXT50");
			listOfStockCompanies.add(stC);
		}
		
		httpGet = new HttpGet(niftyUrl3);
		response =httpClient.execute(httpGet);
		reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		result = new StringBuilder();
		line="";
		while((line=reader.readLine())!=null) {
			result.append(line);
		}
		jsonResponse = new JSONObject(result.toString());
		dataArray = jsonResponse.getJSONArray("data");
		
		for(int i=0;i<dataArray.length();i++) {
			JSONObject dataObject =dataArray.getJSONObject(i);
			if(!dataObject.has("meta"))
				continue;
			JSONObject metaObject = dataObject.getJSONObject("meta");
			
			if(!metaObject.has("companyName")||!metaObject.has("symbol"))
				continue;
			StockCompany stC = new StockCompany();
			stC.setCompanyName(metaObject.getString("companyName"));
			stC.setCompanySymbol(metaObject.getString("symbol"));
			stC.setNiftyType("NIFTYMIDCAP100");
			listOfStockCompanies.add(stC);
		}
		
		httpGet = new HttpGet(niftyUrl4);
		response =httpClient.execute(httpGet);
		reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		result = new StringBuilder();
		line="";
		while((line=reader.readLine())!=null) {
			result.append(line);
		}
		jsonResponse = new JSONObject(result.toString());
		dataArray = jsonResponse.getJSONArray("data");
		
		for(int i=0;i<dataArray.length();i++) {
			JSONObject dataObject =dataArray.getJSONObject(i);
			if(!dataObject.has("meta"))
				continue;
			JSONObject metaObject = dataObject.getJSONObject("meta");
			
			if(!metaObject.has("companyName")||!metaObject.has("symbol"))
				continue;
			StockCompany stC = new StockCompany();
			stC.setCompanyName(metaObject.getString("companyName"));
			stC.setCompanySymbol(metaObject.getString("symbol"));
			stC.setNiftyType("NIFTYSMALLCAP100");
			listOfStockCompanies.add(stC);
		}
		}
		catch(Exception e) {
			
		}
		stockCompanyServiceImpl.addListOfStockCompanies(listOfStockCompanies);
		System.out.println("Stock Company Batch Completed...");
	}

}
