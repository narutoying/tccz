/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.List;

import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;
import com.tccz.tccz.core.service.query.DiscountQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountQueryServiceImpl.java, v 0.1 2013-9-13 下午5:15:20
 *          narutoying09@gmail.com Exp $
 */
public class DiscountQueryServiceImpl implements DiscountQueryService {

	@Override
	public List<Discount> queryByCondition(DiscountQueryCondition condition) {
		return null;
	}

	@Override
	public PageList<Discount> queryByConditionWithPage(
			DiscountQueryCondition condition) {
		return null;
	}

	@Override
	public Discount queryById(int discountId) {
		return null;
	}

}
