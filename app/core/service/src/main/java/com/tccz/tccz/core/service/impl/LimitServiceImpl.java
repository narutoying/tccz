/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.Limit;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.LimitType;
import com.tccz.tccz.core.model.result.LimitControlResult;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.query.BankBizQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitServiceImpl.java, v 0.1 2013-9-12 下午3:43:11
 *          narutoying09@gmail.com Exp $
 */
public class LimitServiceImpl implements LimitService {

	@Autowired
	private BankBizQueryService bankBizQueryService;

	/**
	 * @see com.tccz.tccz.core.service.LimitService#calculateLimit(com.tccz.tccz.core.model.BusinessSide,
	 *      java.util.Date, com.tccz.tccz.core.model.enums.LimitType)
	 */
	@Override
	public Limit calculateLimit(BusinessSide businessSide, Date calDate,
			LimitType limitType) {
		if (calDate == null) {
			calDate = new Date();
		}
		if (limitType == LimitType.COMPOSITE_CREDIT) {
			return getTotalLimit();
		} else if (limitType == LimitType.AVAILABLE) {
			return getAvailableLimit(businessSide, calDate);
		} else if (ocuppyLimitTypes().contains(limitType)) {
			Limit result = new Limit();
			result.setLimitType(limitType);
			Money money = new Money();
			result.setMoney(money);
			int bizSideId = businessSide.getId();
			if (businessSide instanceof Person) {
				// 个人只有流贷业务，无其他业务
				if (limitType == LimitType.FLOATING_LOAN_USED) {
					Map<String, Object> extraParams = new HashMap<String, Object>();
					extraParams.put("isPerson", true);
					money.addTo(calculteOccupiedMoneyForFL(bankBizQueryService
							.query(FloatingLoan.class, bizSideId, calDate,
									null, extraParams), calDate));
				}
			} else if (businessSide instanceof Enterprise) {
				if (limitType == LimitType.FLOATING_LOAN_USED) {
					money.addTo(calculteOccupiedMoneyForFL(bankBizQueryService
							.query(FloatingLoan.class, bizSideId, calDate,
									null, null), calDate));
				} else if (limitType == LimitType.BANDAR_NOTE_USED) {
					money.addTo(calculteOccupiedMoneyForBN(bankBizQueryService
							.query(BandarNote.class, bizSideId, calDate, null,
									null), calDate));
				} else if (limitType == LimitType.DISCOUNT_USED) {
					money.addTo(calculteOccupiedMoneyForDC(bankBizQueryService
							.query(Discount.class, bizSideId, calDate, null,
									null), calDate));
				}
			} else {
				throw new RuntimeException("不支持的业务方" + businessSide);
			}
			return result;
		} else {
			throw new RuntimeException("不支持的额度计算类型");
		}
	}

	/**
	 * 计算可用额度
	 * 
	 * @param businessSide
	 * @param calDate
	 * @param limitType
	 * @return
	 */
	private Limit getAvailableLimit(BusinessSide businessSide, Date calDate) {
		Limit result = new Limit();
		result.setLimitType(LimitType.AVAILABLE);
		List<LimitType> ocuppyLimitTypes = ocuppyLimitTypes();
		Money ocuppied = new Money();
		for (LimitType type : ocuppyLimitTypes) {
			ocuppied.addTo(calculateLimit(businessSide, calDate, type)
					.getMoney());
		}
		result.setMoney(getTotalLimit().getMoney().subtract(ocuppied));
		return result;
	}

	/**
	 * 计算总额度
	 * 
	 * @return
	 */
	private Limit getTotalLimit() {
		Limit result = new Limit();
		result.setLimitType(LimitType.COMPOSITE_CREDIT);
		// TODO 后续从系统配置参数中获取
		result.setMoney(new Money("10000000.00"));
		return result;
	}

	private Money calculteOccupiedMoneyForFL(List<FloatingLoan> query,
			Date calDate) {
		Money result = new Money();
		if (!CollectionUtils.isEmpty(query)) {
			for (FloatingLoan loan : query) {
				if (loan.occupyLimit(calDate)) {
					result.addTo(loan.getAmount());
				}
			}
		}
		return result;
	}

	private Money calculteOccupiedMoneyForBN(List<BandarNote> query,
			Date calDate) {
		Money result = new Money();
		if (!CollectionUtils.isEmpty(query)) {
			for (BandarNote item : query) {
				if (item.occupyLimit(calDate)) {
					result.addTo(item.getAmount());
				}
			}
		}
		return result;
	}

	private Money calculteOccupiedMoneyForDC(List<Discount> query, Date calDate) {
		Money result = new Money();
		if (!CollectionUtils.isEmpty(query)) {
			for (Discount item : query) {
				if (item.occupyLimit(calDate)) {
					result.addTo(item.getAmount());
				}
			}
		}
		return result;
	}

	private List<LimitType> ocuppyLimitTypes() {
		return Arrays.asList(LimitType.FLOATING_LOAN_USED,
				LimitType.BANDAR_NOTE_USED, LimitType.DISCOUNT_USED);
	}

	@Override
	public LimitControlResult isOverLimit(BusinessSide businessSide,
			Date calDate, Money newAmount) {
		LimitControlResult result = new LimitControlResult();
		Money total = getTotalLimit().getMoney();
		result.setTotal(total);
		Money available = getAvailableLimit(businessSide, calDate).getMoney();
		result.setAvailable(available);
		result.setCompareAmount(newAmount);
		boolean overLimit = false;
		if (newAmount.subtract(available).getCent() > 0) {
			overLimit = true;
		}
		result.setOverLimit(overLimit);
		return result;
	}

}
