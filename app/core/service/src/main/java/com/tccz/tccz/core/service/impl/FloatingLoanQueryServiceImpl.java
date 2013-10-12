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

import com.tccz.tccz.common.dal.daointerface.FloatingLoanDAO;
import com.tccz.tccz.common.dal.dataobject.FloatingLoanDO;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.query.FloatingLoanQueryCondition;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;
import com.tccz.tccz.core.service.query.FloatingLoanQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanQueryServiceImpl.java, v 0.1 2013-10-12 下午2:37:43
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanQueryServiceImpl implements FloatingLoanQueryService {

	@Autowired
	private FloatingLoanDAO floatingLoanDAO;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	/**
	 * @see com.tccz.tccz.core.service.query.FloatingLoanQueryService#queryByCondition(com.tccz.tccz.core.model.query.FloatingLoanQueryCondition)
	 */
	@Override
	public PageList<FloatingLoan> queryByCondition(
			FloatingLoanQueryCondition condition) {
		PageList<FloatingLoan> result = new PageList<FloatingLoan>();
		int pageSize = condition.getLimit();
		com.tccz.tccz.dal.util.PageList byCondition = floatingLoanDAO
				.getByCondition(condition.getLoanerName(),
						condition.getBizSideType(), condition.isShowExpire(),
						condition.getExpireStart(), condition.getExpireEnd(),
						pageSize,
						PageUtil.getOffset(pageSize, condition.getPage()));
		result.setDataList(convertToDomains(byCondition));
		result.setPaginator(byCondition.getPaginator());
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<FloatingLoan> convertToDomains(
			@SuppressWarnings("rawtypes") List list) {
		if (!CollectionUtils.isEmpty(list)) {
			List<FloatingLoan> result = new ArrayList<FloatingLoan>();
			for (FloatingLoanDO o : (List<FloatingLoanDO>) list) {
				result.add(ObjectConvertor.convertToFloatingLoan(o,
						businessSideQueryService));
			}
			return result;
		}
		return Collections.emptyList();
	}

	/**
	 * @see com.tccz.tccz.core.service.query.FloatingLoanQueryService#queryById(int)
	 */
	@Override
	public FloatingLoan queryById(int itemId) {
		return ObjectConvertor.convertToFloatingLoan(
				floatingLoanDAO.getById(itemId), businessSideQueryService);
	}

	/**
	 * @see com.tccz.tccz.core.service.query.FloatingLoanQueryService#queryListByExpireDate(int,
	 *      java.util.Date, java.util.Date)
	 */
	@Override
	public List<FloatingLoan> queryListByExpireDate(int bizSideId,
			Date expireStart, Date expireEnd) {
		return convertToDomains(floatingLoanDAO.getByExpireDate(bizSideId,
				expireStart, expireEnd));
	}

}
