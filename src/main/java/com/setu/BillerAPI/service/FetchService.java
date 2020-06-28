package com.setu.BillerAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setu.BillerAPI.dao.BillUpdateDao;
import com.setu.BillerAPI.dao.FetchDao;
import com.setu.BillerAPI.dto.PaymentUpdateRequest;
import com.setu.BillerAPI.entity.BillEntity;
import com.setu.BillerAPI.entity.CustomerEntity;
import com.setu.BillerAPI.util.CustomBadRequestException;
import com.setu.BillerAPI.util.UserNotFoundException;

@Service
public class FetchService {

	@Autowired
	FetchDao fetchDao;
	
	@Autowired
	BillUpdateDao billUpdateDao;
	
	public CustomerEntity getCustomerDetail(String number) {
		try {
		CustomerEntity customerDetail = fetchDao.getCustomerDetail(number);
		
		return customerDetail;
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
	}
	
	public BillEntity getBillDetail(String number) {
		
		BillEntity billEntity = fetchDao.getBillDetail(number);
		
		return billEntity;
	}
	
	public String updateBill(PaymentUpdateRequest request) {
		try {
		 String updateBill = billUpdateDao.updateBill(request);
		 return updateBill;
		}catch(UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}catch(CustomBadRequestException e) {
			throw new CustomBadRequestException(e.getMessage());
		}
	}

	public boolean isValidAuthToken(String authToken) {
		if("5e53a0d698dd15482ffb006e".equals(authToken))
			return true;
		return false;
	}
}

