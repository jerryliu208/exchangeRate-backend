package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.calculate_record;
import com.example.model.calculate_result;
import com.example.service.calculateService;
import com.example.service.selectService;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class controller {
	/////////////////////////////////////////////////////
	@Autowired
	selectService sService; //導入服務層
	
	//取得所有資料
	@GetMapping("/calculate")
	@ResponseBody
	public List<calculate_record> getAllData() {
		return sService.getAllData(); //返回服務層之結果
	}
	
	@Autowired
	calculateService cs;
	////資料
	@PostMapping("/calculate")
	@ResponseBody
	public calculate_result calculate(@RequestBody String str) {
		return cs.calculate(str); //返回服務層之結果
	}
}
