package com.setu.BillerAPI.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.setu.BillerAPI.dto.Response;

/**
 * @author Sagar Maharana
 *  
 * @apiNote This Service is for Response to the negative case as well as positive case
 * 
 * {@code}success() is used for in successful case
 *  and failure() for negative case 
 * 
 * */
@Service
public interface IResponseService {
	public Response success(HttpStatus httpStatusCode);
	public Response success(String message,HttpStatus httpStatusCode);
	public Response success(String message,HttpStatus httpStatusCode,Object data);
	public Response failure(HttpStatus httpStatusCode);
	public Response failure(String message,HttpStatus httpStatusCode);
	public Response failure(String message,HttpStatus httpStatusCode,Object data);
}
