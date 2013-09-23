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
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.service.DiscountManageService;
import com.tccz.tccz.core.service.ObjectConvertor;

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

	/**
	 * @see com.tccz.tccz.core.service.DiscountManageService#createDiscount(com.tccz.tccz.core.model.Discount)
	 */
	@Override
	public CommonResult createDiscount(final Discount discount) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						discountDAO.insert(ObjectConvertor
								.convertToDiscountDO(discount));
						CommonResult.buildResult(result, true, "创建贴现成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(discount.getAmount());
						ParaCheckUtil.checkParaNotNull(discount.getProposer());
						// TODO 校验是否存在该企业
						ParaCheckUtil.checkParaNotNull(discount.getState());
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.DiscountManageService#deleteDiscount(int)
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
	 * @see com.tccz.tccz.core.service.DiscountManageService#updateDiscount(com.tccz.tccz.core.model.Discount)
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
						DiscountChangeDO discountChange = new DiscountChangeDO();
						discountChange.setDiscountId(discount.getId());
						discountChange.setState(discount.getState().getCode());
						discountChangeDAO.insert(discountChange);
						CommonResult.buildResult(result, true, "创建贴现成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(discount.getAmount());
						ParaCheckUtil.checkParaNotNull(discount.getProposer());
						DiscountState state = discount.getState();
						ParaCheckUtil.checkParaNotNull(state);
						// 状态校验
						if (state == DiscountState.COLLECTED) {
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
}
