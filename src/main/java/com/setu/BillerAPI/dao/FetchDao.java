package com.setu.BillerAPI.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.setu.BillerAPI.entity.BillEntity;
import com.setu.BillerAPI.entity.CustomerEntity;
import com.setu.BillerAPI.util.UserNotFoundException;

@Repository
public class FetchDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CustomerEntity getCustomerDetail(String number) {
		List<CustomerEntity> entity = jdbcTemplate.query("SELECT * FROM customert where phone = ?",new Object[] { number },
				(resultSet, rowNum) -> new CustomerEntity(resultSet.getString("name"),resultSet.getInt("id"),resultSet.getString("phone")));
		if(entity.isEmpty())
			throw new UserNotFoundException("customer-not-found");
		return entity.get(0);		
	}
	public BillEntity getBillDetail(String number) {
		List<BillEntity> entity = jdbcTemplate.query("SELECT * FROM bill where phone = ?",new Object[] { number },
				(resultSet, rowNum) -> new BillEntity(resultSet.getInt("id"),resultSet.getString("phone"),resultSet.getString("status"),resultSet.getString("amount"),resultSet.getLong("due_date")));
		return entity.get(0);		
	}
	
}
