package com.setu.BillerAPI.entity;

public class BillEntity {
	private int id; 
	private String phone;
	private String status;
	private String amount;
	private long due_date;
	
	
	
	public BillEntity(int id, String phone, String status, String amount, long due_date) {
		this.id = id;
		this.phone = phone;
		this.status = status;
		this.amount = amount;
		this.due_date = due_date;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public long getDue_date() {
		return due_date;
	}
	public void setDue_date(long due_date) {
		this.due_date = due_date;
	}

	
}
