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
import com.tccz.tccz.core.service.BankBizQueryService;
import com.tccz.tccz.core.service.LimitService;

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
		Limit result = new Limit();
		result.setLimitType(limitType);
		if (limitType == LimitType.COMPOSITE_CREDIT) {
			// TODO 后续从系统配置参数中获取
			result.setMoney(new Money("10000000.00"));
		} else if (limitType == LimitType.AVAILABLE) {
			List<LimitType> ocuppyLimitTypes = ocuppyLimitTypes();
			Money ocuppied = new Money();
			for (LimitType type : ocuppyLimitTypes) {
				ocuppied.add(calculateLimit(businessSide, calDate, type)
						.getMoney());
			}
			result.setMoney(calculateLimit(businessSide, calDate,
					LimitType.COMPOSITE_CREDIT).getMoney().subtract(ocuppied));
		} else if (ocuppyLimitTypes().contains(limitType)) {
			Money money = new Money();
			result.setMoney(money);
			if (businessSide instanceof Person) {
				// 个人只有流贷业务，无其他业务
				if (limitType == LimitType.FLOATING_LOAN) {
					Map<String, Object> extraParams = new HashMap<String, Object>();
					extraParams.put("isPerson", true);
					money.add(calculteOccupiedMoneyForFL(bankBizQueryService
							.query(FloatingLoan.class, calDate, null,
									extraParams), calDate));
				}
			} else if (businessSide instanceof Enterprise) {
				if (limitType == LimitType.FLOATING_LOAN) {
					money.add(calculteOccupiedMoneyForFL(bankBizQueryService
							.query(FloatingLoan.class, calDate, null, null),
							calDate));
				} else if (limitType == LimitType.BANDAR_NOTE) {
					money.add(calculteOccupiedMoneyForBN(bankBizQueryService
							.query(BandarNote.class, calDate, null, null),
							calDate));
				} else if (limitType == LimitType.DISCOUNT) {
					money.add(calculteOccupiedMoneyForDC(bankBizQueryService
							.query(Discount.class, calDate, null, null),
							calDate));
				}
			} else {
				throw new RuntimeException("不支持的业务方" + businessSide);
			}
		} else {
			throw new RuntimeException("不支持的额度计算类型");
		}
		return result;
	}

	private Money calculteOccupiedMoneyForFL(List<FloatingLoan> query,
			Date calDate) {
		Money result = new Money();
		if (!CollectionUtils.isEmpty(query)) {
			for (FloatingLoan loan : query) {
				if (loan.occupyLimit(calDate)) {
					result.add(loan.getAmount());
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
					result.add(item.getAmount());
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
					result.add(item.getAmount());
				}
			}
		}
		return result;
	}

	private List<LimitType> ocuppyLimitTypes() {
		return Arrays.asList(LimitType.FLOATING_LOAN, LimitType.BANDAR_NOTE,
				LimitType.DISCOUNT);
	}

}
