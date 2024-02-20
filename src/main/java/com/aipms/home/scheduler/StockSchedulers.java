package com.aipms.home.scheduler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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



import com.aipms.home.model.StockCompany;
import com.aipms.home.service.impl.StockCompanyServiceImpl;

@Component
public class StockSchedulers {
	@Autowired
	StockCompanyServiceImpl stockCompanyServiceImpl;
	

	

	
	@Scheduled(cron="${stock.company.scheduler}")
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
		System.out.println("Stock Company Batch Started...");
	}

}
