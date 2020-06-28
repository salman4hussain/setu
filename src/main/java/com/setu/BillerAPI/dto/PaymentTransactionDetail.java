package com.setu.BillerAPI.dto;

import java.io.Serializable;

public class PaymentTransactionDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String amountPaid = null;
	private String date = null;
	private String id = null;
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

	
}
