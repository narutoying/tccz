/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.BandarNoteDAO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.template.CommonManageTemplate;
import com.tccz.tccz.common.util.template.callback.CommonManageCallback;
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.manage.BandarNoteManageService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteManageServiceImpl.java, v 0.1 2013-10-9 下午5:06:27
 *          narutoying09@gmail.com Exp $
 */
public class BandarNoteManageServiceImpl implements BandarNoteManageService {

	@Autowired
	private CommonManageTemplate commonManageTemplate;

	@Autowired
	private LimitService limitService;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@Autowired
	private BandarNoteDAO bandarNoteDAO;

	/**
	 * @see com.tccz.tccz.core.service.manage.BandarNoteManageService#create(com.tccz.tccz.core.model.BandarNote)
	 */
	@Override
	public CommonResult create(BandarNote bandarNote) {
		CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BandarNoteManageService#delete(int)
	 */
	@Override
	public CommonResult delete(int bandarNoteId) {
		return null;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BandarNoteManageService#update(com.tccz.tccz.core.model.BandarNote)
	 */
	@Override
	public CommonResult update(BandarNote bandarNote) {
		return null;
	}

}
