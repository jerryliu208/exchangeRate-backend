package com.example.service;


import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.calculate_result;

@Service
public class calculateService {
	////////////////計算//////////////////
	public calculate_result calculate(String data){
		JSONObject client = new JSONObject(data);
		JSONObject nation = exchangeRate();
		JSONObject Exrate = new JSONObject();
		
		float price = (float)client.getDouble("price");
		float discount = (float)client.getDouble("discount");
		String currency = client.getString("currency");
		
		double rate = 1;
		double result = 0;
		
		switch(currency) {
			case "USD":
				Exrate = nation.getJSONObject("USDTWD");
				rate = Exrate.getDouble("Exrate");
				result = price*rate;
				break;
			case "TWD":
				/*JSONObject testJo = exRate.getJSONObject("USDTWD");
				rate = testJo.getDouble("Exrate");*/
				result = price;
				break;
			case "JPY":
				Exrate = nation.getJSONObject("USDJPY");
				JSONObject Exrate1 = nation.getJSONObject("USDTWD");
				
				rate = Exrate.getDouble("Exrate");
				double rate1 = Exrate1.getDouble("Exrate");
				
				result = price/(rate/rate1);
				break;
		}
		
		calculate_result cr = new calculate_result();
		cr.setCurrency(currency);
		cr.setDiscount(discount);
		cr.setPrice(price);
		cr.setRate(rate);
		cr.setResult(result);
		
		return cr;
	}
	
	public JSONObject exchangeRate() {
		String url = "https://tw.rter.info/capi.php";
		
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(url, String.class);
		
		JSONObject jo = new JSONObject(result);
		return jo;
	}
}