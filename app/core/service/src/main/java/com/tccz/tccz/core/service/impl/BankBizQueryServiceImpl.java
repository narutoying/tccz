/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.Constants;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.enums.LoanBizSideType;
import com.tccz.tccz.core.service.query.BandarNoteQueryService;
import com.tccz.tccz.core.service.query.BankBizQueryService;
import com.tccz.tccz.core.service.query.DiscountQueryService;
import com.tccz.tccz.core.service.query.FloatingLoanQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BankBizQueryServiceImpl.java, v 0.1 2013-9-13 下午2:08:52
 *          narutoying09@gmail.com Exp $
 */
public class BankBizQueryServiceImpl implements BankBizQueryService {

	@Autowired
	private DiscountQueryService discountQueryService;

	@Autowired
	private BandarNoteQueryService bandarNoteQueryService;

	@Autowired
	private FloatingLoanQueryService floatingLoanQueryService;

	/**
	 * @see com.tccz.tccz.core.service.query.BankBizQueryService#query(java.lang.Class,
	 *      java.lang.Integer, java.util.Date, java.util.Date, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> query(Class<T> classType, String bizSideId,
			Date expireDateStart, Date expireDateEnd,
			Map<String, Object> extraParams) {
		if (classType == Discount.class) {
			return (List<T>) discountQueryService.queryDiscounts(bizSideId,
					expireDateStart, expireDateEnd);
		} else if (classType == BandarNote.class) {
			return (List<T>) bandarNoteQueryService.queryListByExpireDate(
					bizSideId, expireDateStart, expireDateEnd);
		} else if (classType == FloatingLoan.class) {
			Object object = null;
			if (extraParams != null) {
				object = extraParams.get(Constants.LoanBizSideType);
			}
			String bizSideType = (object == null ? LoanBizSideType.CORPORATE
					.getCode() : ((LoanBizSideType) object).getCode());
			return (List<T>) floatingLoanQueryService.queryListByRepay(
					bizSideId, bizSideType, false);
		}
		return Collections.emptyList();
	}
}
