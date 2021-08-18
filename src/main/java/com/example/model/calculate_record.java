package com.example.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class calculate_record {
	String currency;
	double rate;
	double price;
	double discount;
	double result;
	Timestamp record_time;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public Timestamp getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Timestamp record_time) {
		this.record_time = record_time;
	}
	
}
