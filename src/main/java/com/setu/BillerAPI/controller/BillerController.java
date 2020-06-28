package com.setu.BillerAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setu.BillerAPI.dto.FetchBillRequest;
import com.setu.BillerAPI.dto.FetchBillResponse;
import com.setu.BillerAPI.dto.PaymentUpdateRequest;
import com.setu.BillerAPI.entity.BillEntity;
import com.setu.BillerAPI.entity.CustomerEntity;
import com.setu.BillerAPI.service.FetchService;
import com.setu.BillerAPI.util.CommonUtil;
import com.setu.BillerAPI.util.CustomBadRequestException;
import com.setu.BillerAPI.util.UserNotFoundException;

@RestController
@RequestMapping("api/v1")
public class BillerController extends BaseController {

	@Autowired
	private FetchService fetchService;
	private final String HEADER_AUTH_TOKEN = "X-API-KEY";

	@PostMapping("/fetch-bill")
	public ResponseEntity<?> fetchBill(@RequestHeader(HEADER_AUTH_TOKEN) String authToken,
			@RequestBody FetchBillRequest billRequestEntity) {
		if (!fetchService.isValidAuthToken(authToken)) {
			return resultResponseUnauthorized();
		}
		try {
			FetchBillResponse responseEntity = new FetchBillResponse();
			CustomerEntity customerDetail = fetchService.getCustomerDetail(billRequestEntity.getMobileNumber());
			BillEntity billEntity = fetchService.getBillDetail(billRequestEntity.getMobileNumber());
			responseEntity.setCustomerName(customerDetail.getName());
			responseEntity.setDueAmount(billEntity.getAmount());
			responseEntity.setDueDate(CommonUtil.convertMillisToDate(billEntity.getDue_date()));
			responseEntity.setRefID(billEntity.getId() + "");
			return resultResponseSuccess(SUCCESS, responseEntity);
		} catch (UserNotFoundException e) {
			return resultResponseNotFound(e.getMessage());
		} catch (Exception e) {
			return resultResponseInternalServerError("unhandled-error");
		}
	}

	@PostMapping("/payment-update")
	public ResponseEntity<?> paymentUpdate(@RequestHeader(HEADER_AUTH_TOKEN) String authToken,
			@RequestBody PaymentUpdateRequest paymentUpdateReq) {

		try {
			if (!fetchService.isValidAuthToken(authToken)) {
				return resultResponseUnauthorized();
			}

			String response = fetchService.updateBill(paymentUpdateReq);
			Map<String, String> respnseMap = new HashMap<>();
			if ("Duplicate".equals(response))
				return resultResponseNotAcceptable("DUPLICATE");
			respnseMap.put("ackID", response);
			return resultResponseSuccess(SUCCESS, respnseMap);

		} catch (UserNotFoundException e) {
			return resultResponseNotFound(e.getMessage());
		} catch (CustomBadRequestException e) {
			return resultResponseBadRequest(e.getMessage());
		} catch (Exception e) {
			return resultResponseInternalServerError("unhandled-error");
		}
	}
}
