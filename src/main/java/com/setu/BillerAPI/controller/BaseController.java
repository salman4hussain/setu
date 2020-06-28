package com.setu.BillerAPI.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.setu.BillerAPI.dto.Response;
import com.setu.BillerAPI.service.IResponseService;

public class BaseController {
	private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	/** The Constant REQUEST_IS_UNAUTHORIZED. */
	private static final String REQUEST_IS_UNAUTHORIZED = "Request is unauthorized";

	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";

	/** The Constant FAILED. */
	public static final String FAILED = "failed";

	@Autowired
	private IResponseService response;

	protected int sizeOf(List<?> list) {
		return list == null ? 0 : list.size();
	}

	protected ResponseEntity<String> newresponseNotFound(Logger logger, String msg) {
		logger.error(msg);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

	protected ResponseEntity<String> newresponseUnauthorized(Logger logger) {
		logger.error(REQUEST_IS_UNAUTHORIZED);
		return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED).body(REQUEST_IS_UNAUTHORIZED);
	}

	
	protected ResponseEntity<String> newresponseNotAcceptable(Logger logger, String body) {
		logger.error(body);
		return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_ACCEPTABLE).body(body);
	}


	protected ResponseEntity<Response> resultResponseNotFound(String msg) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.failure(msg, HttpStatus.NOT_FOUND));
	}

	protected ResponseEntity<Response> resourceResponseNotFound(Logger logger, String msg) {
		logger.error(HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.failure(msg, HttpStatus.NOT_FOUND));
	}


	protected ResponseEntity<Response> resultResponseNotFound(String msg, Boolean isRegistered) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.failure(msg, HttpStatus.NOT_FOUND));
	}

	protected ResponseEntity<Response> resultResponseUnauthorized() {
		return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
				.body(response.failure("auth-error", HttpStatus.FORBIDDEN));
	}

	protected ResponseEntity<Response> resultResponseUnauthorized(String message) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED)
				.body(response.failure(message, HttpStatus.UNAUTHORIZED));
	}

	protected ResponseEntity<Response> requestUnauthorized(Logger logger, String message) {
		logger.error(message);
		return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED)
				.body(response.failure(message, HttpStatus.UNAUTHORIZED));
	}

	protected ResponseEntity<Response> resultResponseNotAcceptable(String message) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_ACCEPTABLE)
				.body(response.failure(message, HttpStatus.NOT_ACCEPTABLE));
	}

	protected ResponseEntity<Response> resultResponseUnprocessableEntity(String message) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY)
				.body(response.failure(message, HttpStatus.UNPROCESSABLE_ENTITY));
	}

	protected ResponseEntity<Response> resultResponseForbidden(String message) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
				.body(response.failure(message, HttpStatus.FORBIDDEN));
	}

	protected ResponseEntity<Response> resultResponseInternalServerError(String message) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
				.body(response.failure(message, HttpStatus.INTERNAL_SERVER_ERROR));
	}

	protected ResponseEntity<Response> resultResponseBadRequest(String message) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST)
				.body(response.failure(message, HttpStatus.BAD_REQUEST));
	}

	protected ResponseEntity<Response> resultResponseSuccess(String message, Object data) {

		return ResponseEntity.status(org.springframework.http.HttpStatus.OK)
				.body(response.success(message, HttpStatus.OK, data));
	}

	protected ResponseEntity<Response> resultResponseConflict(String message) {

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response.failure(message, HttpStatus.CONFLICT));
	}

	protected ResponseEntity<Response> resultResponseMaxLimitReached(String message) {

		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.body(response.failure(message, HttpStatus.TOO_MANY_REQUESTS));
	}
}
