package com.setu.BillerAPI.dto;

public class Response {

	private String status; // indicate the status Code of HttpResponse e.g 404 for Not Found
	private Object data; // for adding data parameter for success case

	
	
	public Response(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public Response() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
