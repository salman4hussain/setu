package com.setu.BillerAPI.dto;

import java.io.Serializable;

public class PaymentUpdateRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String refID = null;
	private PaymentTransactionDetail transaction = null;
	
	public String getRefID() {
		return refID;
	}
	public void setRefID(String refID) {
		this.refID = refID;
	}
	public PaymentTransactionDetail getTransaction() {
		return transaction;
	}
	public void setTransaction(PaymentTransactionDetail transaction) {
		this.transaction = transaction;
	}
	
}
