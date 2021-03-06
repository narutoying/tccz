/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.FloatingLoanDAO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.ParaCheckUtil;
import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.common.util.template.CommonManageTemplate;
import com.tccz.tccz.common.util.template.callback.CommonManageCallback;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.enums.LoanBizSideType;
import com.tccz.tccz.core.model.result.LimitControlResult;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.manage.FloatingLoanManageService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;
import com.tccz.tccz.core.service.query.FloatingLoanQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanManageServiceImpl.java, v 0.1 2013-10-12 下午4:47:25
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanManageServiceImpl implements FloatingLoanManageService {

	@Autowired
	private CommonManageTemplate commonManageTemplate;

	@Autowired
	private LimitService limitService;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@Autowired
	private FloatingLoanDAO floatingLoanDAO;

	@Autowired
	private FloatingLoanQueryService floatingLoanQueryService;

	/**
	 * @see com.tccz.tccz.core.service.manage.FloatingLoanManageService#create(com.tccz.tccz.core.model.FloatingLoan)
	 */
	@Override
	public CommonResult create(final FloatingLoan floatingLoan) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						LoanBizSideType bizSideType = floatingLoan
								.getBizSideType();
						BusinessSide businessSide = null;
						if (bizSideType == LoanBizSideType.CORPORATE) {
							businessSide = businessSideQueryService
									.queryEnterpriseByInstitudeCode(floatingLoan
											.getLoaner().getIdentifier());
						} else if (bizSideType == LoanBizSideType.PRIVATE) {
							businessSide = businessSideQueryService
									.queryPersonByIdCard(floatingLoan
											.getLoaner().getIdentifier(), false);
						}
						LimitControlResult controlResult = limitService
								.isOverLimit(businessSide, null,
										floatingLoan.occupyMoney());
						if (!controlResult.isOverLimit()) {
							floatingLoanDAO.insert(ObjectConvertor
									.convertToFloatingLoanDO(floatingLoan));
							CommonResult.buildResult(result, true, "创建流贷成功");
						} else {
							CommonResult.buildResult(result, false, "创建流贷失败，"
									+ controlResult.getResultString());
						}
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(floatingLoan);
						ParaCheckUtil.checkParaNotNull(floatingLoan
								.getBizSideType());
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.FloatingLoanManageService#delete(int)
	 */
	@Override
	public CommonResult delete(final int floatingLoanId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						floatingLoanDAO.delete(floatingLoanId);
						CommonResult.buildResult(result, true, "创建流贷成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNegative(floatingLoanId);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.FloatingLoanManageService#update(com.tccz.tccz.core.model.FloatingLoan)
	 */
	@Override
	public CommonResult update(final FloatingLoan floatingLoan) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						floatingLoanDAO.update(ObjectConvertor
								.convertToFloatingLoanDO(floatingLoan));
						CommonResult.buildResult(result, true, "创建流贷成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(floatingLoan);
						ParaCheckUtil.checkParaNotNull(floatingLoan
								.getBizSideType());
						FloatingLoan loan = floatingLoanQueryService
								.queryById(floatingLoan.getId());
						Money old = loan.getAmount();
						Money amount = floatingLoan.getAmount();
						if (amount.greaterThan(old)) {
							throw new CommonException("更新金额[" + amount
									+ "]不能超过原来金额[" + old + "]");
						}
					}
				});
		return result;
	}

}
