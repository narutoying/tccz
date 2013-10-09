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
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.service.query.BandarNoteQueryService;
import com.tccz.tccz.core.service.query.BankBizQueryService;
import com.tccz.tccz.core.service.query.DiscountQueryService;

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

	/**
	 * @see com.tccz.tccz.core.service.query.BankBizQueryService#query(java.lang.Class,
	 *      java.lang.Integer, java.util.Date, java.util.Date, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> query(Class<T> classType, Integer bizSideId,
			Date expireDateStart, Date expireDateEnd,
			Map<String, Object> extraParams) {
		if (classType == Discount.class) {
			return (List<T>) discountQueryService.queryDiscounts(bizSideId,
					expireDateStart, expireDateEnd);
		} else if (classType == BandarNote.class) {
			return (List<T>) bandarNoteQueryService.queryListByExpireDate(
					bizSideId, expireDateStart, expireDateEnd);
		}
		return Collections.emptyList();
	}
}
