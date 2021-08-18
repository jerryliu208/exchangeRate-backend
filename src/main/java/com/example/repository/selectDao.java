package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.model.calculate_record;

@Repository
public class selectDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	///////////////取得所有資料/////////////////
	public List<calculate_record> getAllData() {
		String sql = "SELECT * FROM calculate_record";
		return (List<calculate_record>) jdbcTemplate.query(sql, new RowMapper<calculate_record>(){
			@Override
			public calculate_record mapRow(ResultSet rs, int rowNum) throws SQLException {
				calculate_record cr = new calculate_record();
				cr.setCurrency(rs.getString("currency"));
				cr.setRate(rs.getDouble("rate"));
				cr.setPrice(rs.getDouble("price"));
				cr.setDiscount(rs.getDouble("discount"));
				cr.setResult(rs.getDouble("result"));
				cr.setRecord_time(rs.getTimestamp("record_time"));
				return cr;
			}
		});
	}
}
