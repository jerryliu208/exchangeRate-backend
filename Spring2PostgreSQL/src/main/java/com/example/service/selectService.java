package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.calculate_record;
import com.example.repository.selectDao;

@Service
public class selectService {
	////////////////取得所有資料//////////////////
	@Autowired
	selectDao sDao;
	
	public List<calculate_record> getAllData(){
		List<calculate_record> data = sDao.getAllData();
		return data;
	}
}
