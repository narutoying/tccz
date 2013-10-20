/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.BandarNoteDAO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.ParaCheckUtil;
import com.tccz.tccz.common.util.template.CommonManageTemplate;
import com.tccz.tccz.common.util.template.callback.CommonManageCallback;
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.result.LimitControlResult;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.ObjectConvertor;
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
	public CommonResult create(final BandarNote bandarNote) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						LimitControlResult controlResult = limitService.isOverLimit(
								businessSideQueryService
										.queryEnterpriseByInstitudeCode(bandarNote
												.getDrawer().getIdentifier()),
								null, bandarNote.occupyMoney());
						if (!controlResult.isOverLimit()) {
							bandarNoteDAO.insert(ObjectConvertor
									.convertToBandarNoteDO(bandarNote));
							CommonResult.buildResult(result, true, "创建银票成功");
						} else {
							CommonResult.buildResult(result, false, "创建银票失败，"
									+ controlResult.getResultString());
						}
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(bandarNote);
						bandarNote.verifyData();
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BandarNoteManageService#delete(int)
	 */
	@Override
	public CommonResult delete(final int bandarNoteId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						bandarNoteDAO.delete(bandarNoteId);
						CommonResult.buildResult(result, true, "银票成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNegative(bandarNoteId);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BandarNoteManageService#update(com.tccz.tccz.core.model.BandarNote)
	 */
	@Override
	public CommonResult update(final BandarNote bandarNote) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						bandarNoteDAO.update(ObjectConvertor
								.convertToBandarNoteDO(bandarNote));
						CommonResult.buildResult(result, true, "银票成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(bandarNote);
						bandarNote.verifyData();
					}
				});
		return result;
	}

}
