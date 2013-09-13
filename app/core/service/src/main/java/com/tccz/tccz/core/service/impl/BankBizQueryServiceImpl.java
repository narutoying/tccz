/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tccz.tccz.common.util.DateUtil;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.service.query.BankBizQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BankBizQueryServiceImpl.java, v 0.1 2013-9-13 下午2:08:52
 *          narutoying09@gmail.com Exp $
 */
public class BankBizQueryServiceImpl implements BankBizQueryService {

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
			List<Discount> result = new ArrayList<Discount>();
			result.addAll(mockDiscounts());
			return (List<T>) result;
		}
		return Collections.emptyList();
	}

	private List<Discount> mockDiscounts() {
		try {
			Money amount = new Money("1000000.00");
			List<Discount> result = new ArrayList<Discount>();
			Discount d1 = new Discount();
			d1.setAmount(amount);
			d1.setExpireDate(DateUtil.parseDateNoTime("20130912"));
			d1.setState(DiscountState.COLLECTED);
			result.add(d1);
			Discount d2 = new Discount();
			d2.setAmount(amount);
			d2.setExpireDate(DateUtil.parseDateNoTime("20130913"));
			d2.setState(DiscountState.COLLECTED);
			result.add(d2);
			Discount d3 = new Discount();
			d3.setAmount(amount);
			d3.setExpireDate(DateUtil.parseDateNoTime("20130914"));
			d3.setState(DiscountState.BUY_BACK);
			result.add(d3);
			Discount d4 = new Discount();
			d4.setAmount(amount);
			d4.setExpireDate(DateUtil.parseDateNoTime("20130915"));
			d4.setState(DiscountState.TRANSFER);
			result.add(d4);
			return result;
		} catch (ParseException e) {
			System.err.println(e);
			return Collections.emptyList();
		}
	}

}
