/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.common.dal.daointerface.DiscountChangeDAO;
import com.tccz.tccz.common.dal.daointerface.DiscountDAO;
import com.tccz.tccz.common.dal.dataobject.DiscountDO;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.DiscountChange;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;
import com.tccz.tccz.core.service.query.DiscountQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountQueryServiceImpl.java, v 0.1 2013-9-13 下午5:15:20
 *          narutoying09@gmail.com Exp $
 */
public class DiscountQueryServiceImpl implements DiscountQueryService {

	@Autowired
	private DiscountDAO discountDAO;
	@Autowired
	private DiscountChangeDAO discountChangeDAO;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@SuppressWarnings("unchecked")
	@Override
	public PageList<Discount> queryByCondition(DiscountQueryCondition condition) {
		PageList<Discount> result = new PageList<Discount>();
		Integer pageSize = condition.getLimit();
		com.tccz.tccz.dal.util.PageList list = discountDAO.getByCondition(
				condition.getEnterpriseName(), condition.getBandarNoteNumber(),
				condition.isShowExpire(), condition.isDoPage(), pageSize,
				PageUtil.getOffset(pageSize, condition.getPage()));
		result.setDataList(convertToDomains(list));
		result.setPaginator(list.getPaginator());
		return result;
	}

	private List<Discount> convertToDomains(List list) {
		if (!CollectionUtils.isEmpty(list)) {
			List<Discount> result = new ArrayList<Discount>();
			for (Object o : list) {
				if (o instanceof DiscountDO) {
					DiscountDO data = (DiscountDO) o;
					result.add(ObjectConvertor.convertToDiscount(data, this,
							businessSideQueryService));
				}
			}
			return result;
		}
		return Collections.emptyList();
	}

	@Override
	public Discount queryById(int discountId) {
		return ObjectConvertor
				.convertToDiscount(discountDAO.getById(discountId), this,
						businessSideQueryService);
	}

	@Override
	public List<DiscountChange> queryDiscountChanges(int discountId) {
		return ObjectConvertor.convertToDiscountChangeList(discountChangeDAO
				.getByDiscountId(discountId));
	}

	@Override
	public List<Discount> queryDiscounts(int proposerId, Date expireStart,
			Date expireEnd) {
		return convertToDomains(discountDAO.getByExpireDate(proposerId,
				expireStart, expireEnd));
	}
}
