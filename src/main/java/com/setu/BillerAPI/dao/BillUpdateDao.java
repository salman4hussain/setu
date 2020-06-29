package com.setu.BillerAPI.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.setu.BillerAPI.dto.PaymentUpdateRequest;
import com.setu.BillerAPI.entity.BillEntity;
import com.setu.BillerAPI.entity.TransactionEntity;
import com.setu.BillerAPI.util.CommonUtil;
import com.setu.BillerAPI.util.CustomBadRequestException;
import com.setu.BillerAPI.util.UserNotFoundException;

@Repository
public class BillUpdateDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String updateBill(PaymentUpdateRequest request) {
		synchronized (BillUpdateDao.class) {
			if (isTransactionExist(Integer.parseInt(request.getRefID()), request.getTransaction().getId())) {
				return "Duplicate";
			}
			if (isTransactionRefIdExist(Integer.parseInt(request.getRefID()))) {
				throw new UserNotFoundException("invalid-ref-id");
			}
			List<BillEntity> entity = jdbcTemplate.query("SELECT * FROM bill where id = ?",
					new Object[] { request.getRefID() },
					(resultSet, rowNum) -> new BillEntity(resultSet.getInt("id"), resultSet.getString("phone"),
							resultSet.getString("status"), resultSet.getString("amount"),
							resultSet.getLong("due_date")));
			if (entity.isEmpty())
				throw new UserNotFoundException("invalid-ref-id");

			int balance = Integer.parseInt(entity.get(0).getAmount())
					- Integer.parseInt(request.getTransaction().getAmountPaid());
			if (balance != 0)
				throw new CustomBadRequestException("amount-mismatch");

			String updateQuery = "update bill set amount = ? where id = ?";
			jdbcTemplate.update(updateQuery, balance, request.getRefID());

			String billId = CommonUtil.getAlphaNumericString(6);
			jdbcTemplate.execute("INSERT INTO cust_transaction VALUES (" + "'" + billId + "'," + "'"
					+ request.getTransaction().getId() + "'," + "'" + request.getRefID() + "'," + "'"
					+ request.getTransaction().getAmountPaid() + "'," + "'" + System.currentTimeMillis() + "')");
			return billId;
		}
	}

	private boolean isTransactionExist(int refId, String trans_id) {
		List<TransactionEntity> entity = jdbcTemplate.query(
				"SELECT * FROM cust_transaction where trans_id = ? and ref_id = ?", new Object[] { trans_id, refId },
				(resultSet, rowNum) -> new TransactionEntity(resultSet.getString("id")));
		if (entity.isEmpty())
			return false;
		else
			return true;
	}

	private boolean isTransactionRefIdExist(int refId) {
		List<TransactionEntity> entity = jdbcTemplate.query("SELECT * FROM cust_transaction where ref_id = ?",
				new Object[] { refId }, (resultSet, rowNum) -> new TransactionEntity(resultSet.getString("id")));
		if (entity.isEmpty())
			return false;
		else
			return true;
	}
}
