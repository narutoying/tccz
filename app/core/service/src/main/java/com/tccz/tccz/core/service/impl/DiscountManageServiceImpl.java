/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.DiscountChangeDAO;
import com.tccz.tccz.common.dal.daointerface.DiscountDAO;
import com.tccz.tccz.common.dal.dataobject.DiscountChangeDO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.ParaCheckUtil;
import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.common.util.template.CommonManageTemplate;
import com.tccz.tccz.common.util.template.callback.CommonManageCallback;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.model.result.LimitControlResult;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.manage.DiscountManageService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountManageServiceImpl.java, v 0.1 2013-9-13 下午5:15:48
 *          narutoying09@gmail.com Exp $
 */
public class DiscountManageServiceImpl implements DiscountManageService {

	@Autowired
	private CommonManageTemplate commonManageTemplate;

	@Autowired
	private DiscountDAO discountDAO;

	@Autowired
	private DiscountChangeDAO discountChangeDAO;

	@Autowired
	private LimitService limitService;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	/**
	 * @see com.tccz.tccz.core.service.manage.DiscountManageService#createDiscount(com.tccz.tccz.core.model.Discount)
	 */
	@Override
	public CommonResult createDiscount(final Discount discount) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						LimitControlResult controlResult = limitService
								.isOverLimit(businessSideQueryService
										.queryEnterpriseById(discount
												.getProposer().getId()), null,
										discount.getAmount());
						if (!controlResult.isOverLimit()) {
							int discountId = discountDAO.insert(ObjectConvertor
									.convertToDiscountDO(discount));
							createDiscountChange(discountId, discount
									.getState().getCode());
							CommonResult.buildResult(result, true, "创建贴现成功");
						} else {
							CommonResult.buildResult(result, false, "创建贴现失败，"
									+ controlResult.getResultString());
						}
					}

					@Override
					public void checkParameter() {
						defaultCheck(discount);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.DiscountManageService#deleteDiscount(int)
	 */
	@Override
	public CommonResult deleteDiscount(final int discountId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						discountDAO.delete(discountId);
						CommonResult.buildResult(result, true, "删除贴现成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNegative(discountId);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.DiscountManageService#updateDiscount(com.tccz.tccz.core.model.Discount)
	 */
	@Override
	public CommonResult updateDiscount(final Discount discount) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						discountDAO.update(ObjectConvertor
								.convertToDiscountDO(discount));
						int discountId = discount.getId();
						String stateCode = discount.getState().getCode();
						createDiscountChange(discountId, stateCode);
						CommonResult.buildResult(result, true, "更新贴现成功");
					}

					@Override
					public void checkParameter() {
						defaultCheck(discount);
						// 状态校验
						if (discount.getState() == DiscountState.COLLECTED) {
							Date now = new Date();
							if (now.getTime() < discount.getExpireDate()
									.getTime()) {
								throw new CommonException(
										"当前时间>=到期时间才能修改成“已托收”");
							}
						}
					}
				});
		return result;
	}

	private void createDiscountChange(int discountId, String stateCode) {
		DiscountChangeDO discountChange = new DiscountChangeDO();
		discountChange.setDiscountId(discountId);
		discountChange.setState(stateCode);
		discountChangeDAO.insert(discountChange);
	}

	private void defaultCheck(final Discount discount) {
		ParaCheckUtil.checkParaNotNull(discount.getAmount());
		Enterprise proposer = discount.getProposer();
		ParaCheckUtil.checkParaNotNull(proposer);
		int proposerId = proposer.getId();
		ParaCheckUtil.checkParaPositive(proposerId);
		Enterprise queryEnterpriseById = businessSideQueryService
				.queryEnterpriseById(proposerId);
		if (queryEnterpriseById == null) {
			throw new CommonException("不存在企业id=" + proposerId);
		}
		ParaCheckUtil.checkParaNotNull(discount.getState());
	}
}
