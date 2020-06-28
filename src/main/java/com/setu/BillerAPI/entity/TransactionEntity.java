package com.setu.BillerAPI.entity;

public class TransactionEntity {
	private String id;
	private String trans_id;
	private String ref_id;
	private String amount;
	private long pay_date;
	
	
	public TransactionEntity(String id, String trans_id, String ref_id, String amount, long pay_date) {
		this.id = id;
		this.trans_id = trans_id;
		this.ref_id = ref_id;
		this.amount = amount;
		this.pay_date = pay_date;
	}
	public TransactionEntity(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getRef_id() {
		return ref_id;
	}
	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public long getPay_date() {
		return pay_date;
	}
	public void setPay_date(long pay_date) {
		this.pay_date = pay_date;
	}
	
}
