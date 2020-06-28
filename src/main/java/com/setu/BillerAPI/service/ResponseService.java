package com.setu.BillerAPI.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.setu.BillerAPI.dto.Response;


@Service
public class ResponseService implements IResponseService {

	//@Autowired
	//private Response response;
	
	@Override
	public Response success(HttpStatus httpStatusCode) {
		
		Response response=new Response();
		response.setStatus("SUCCESS");
		response.setData("");
		return response;
	}

	@Override
	public Response success(String message, HttpStatus httpStatusCode) {
		Response response=new Response();
		response.setStatus("SUCCESS");
		response.setData("");
		return response;
	}

	@Override
	public Response success(String message, HttpStatus httpStatusCode, Object data) {
		Response response=new Response();
		response.setStatus("SUCCESS");
		response.setData(data);
		return response;
	}

	@Override
	public Response failure(HttpStatus httpStatusCode) {
		Response response=new Response();
		response.setStatus("FAILURE");
		response.setData("");
		return response;
	}

	@Override
	public Response failure(String message, HttpStatus httpStatusCode) {
		Response response=new Response();
		response.setStatus(message);
		response.setData(null);
		return response;
	}
	
	@Override
	public Response failure(String message, HttpStatus httpStatusCode, Object data) {
		Response response=new Response();
		response.setStatus("FAILURE");
		response.setData(data);
		return response;
	}

	
}
