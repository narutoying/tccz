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

import com.tccz.tccz.common.dal.daointerface.BandarNoteDAO;
import com.tccz.tccz.common.dal.dataobject.BandarNoteDO;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.query.BandarNoteQueryCondition;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.query.BandarNoteQueryService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteQueryServiceImpl.java, v 0.1 2013-10-8 上午10:39:13
 *          narutoying09@gmail.com Exp $
 */
public class BandarNoteQueryServiceImpl implements BandarNoteQueryService {

	@Autowired
	private BandarNoteDAO bandarNoteDAO;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	/**
	 * @see com.tccz.tccz.core.service.query.BandarNoteQueryService#queryByCondition(com.tccz.tccz.core.model.query.BandarNoteQueryCondition)
	 */
	@Override
	public PageList<BandarNote> queryByCondition(
			BandarNoteQueryCondition condition) {
		PageList<BandarNote> result = new PageList<BandarNote>();
		int pageSize = condition.getLimit();
		com.tccz.tccz.dal.util.PageList byCondition = bandarNoteDAO
				.getByCondition(condition.getEnterpriseName(),
						condition.getNumber(), condition.getType(),
						condition.isShowExpire(), condition.getExpireStart(),
						condition.getExpireEnd(), pageSize,
						PageUtil.getOffset(pageSize, condition.getPage()));
		result.setDataList(convertToDomains(byCondition));
		result.setPaginator(byCondition.getPaginator());
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<BandarNote> convertToDomains(
			@SuppressWarnings("rawtypes") List list) {
		if (!CollectionUtils.isEmpty(list)) {
			List<BandarNote> result = new ArrayList<BandarNote>();
			for (BandarNoteDO o : (List<BandarNoteDO>) list) {
				result.add(ObjectConvertor.convertToBandarNote(o,
						businessSideQueryService));
			}
			return result;
		}
		return Collections.emptyList();
	}

	/**
	 * @see com.tccz.tccz.core.service.query.BandarNoteQueryService#queryById(int)
	 */
	@Override
	public BandarNote queryById(int itemId) {
		return ObjectConvertor.convertToBandarNote(
				bandarNoteDAO.getById(itemId), businessSideQueryService);
	}

	/**
	 * @see com.tccz.tccz.core.service.query.BandarNoteQueryService#queryListByExpireDate(int,
	 *      java.util.Date, java.util.Date)
	 */
	@Override
	public List<BandarNote> queryListByExpireDate(String bizSideId,
			Date expireStart, Date expireEnd) {
		return convertToDomains(bandarNoteDAO.getByExpireDate(bizSideId,
				expireStart, expireEnd));
	}

}
