package com.example.service;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.calculate_result;
import com.example.repository.insertDao;

@Service
public class calculateService {
	
	////////////////計算//////////////////
	@Autowired
	insertDao iDao;
	public calculate_result calculate(String data){
		JSONObject client = new JSONObject(data);
		JSONObject nation = exRateApi();
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
				result = price-discount;
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
		
		iDao.resultDao(cr);
		
		return cr;
	}
	
	public JSONObject exRateApi() {
		String url = "https://tw.rter.info/capi.php";
		
		CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String result = restTemplate.getForObject(url, String.class);
		
		JSONObject jo = new JSONObject(result);
		return jo;
	}
}