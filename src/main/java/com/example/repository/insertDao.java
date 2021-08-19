package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.calculate_result;

@Repository
public class insertDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	///////////////將操作資料加入資料庫/////////////////
	public Boolean resultDao(calculate_result cr) {
		String currency = cr.getCurrency();
		double rate = cr.getRate();
		double price = cr.getPrice();
		double discount = cr.getDiscount();
		double result = cr.getResult();
		
		String sql = "INSERT INTO calculate_record (currency, rate, price, discount, result) VALUES (?, ?, ?, ?, ?)";
		
		try {
			
			jdbcTemplate.update(sql, currency, rate, price, discount, result); 
			
			return true; //返回檢查結果
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
