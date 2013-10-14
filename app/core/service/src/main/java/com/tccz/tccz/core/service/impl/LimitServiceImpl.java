/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.BusinessSideSet;
import com.tccz.tccz.core.model.Constants;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.BankBizType;
import com.tccz.tccz.core.model.enums.BusinessSideSetType;
import com.tccz.tccz.core.model.enums.LoanBizSideType;
import com.tccz.tccz.core.model.result.LimitControlResult;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.query.BankBizQueryService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitServiceImpl.java, v 0.1 2013-9-12 下午3:43:11
 *          narutoying09@gmail.com Exp $
 */
public class LimitServiceImpl implements LimitService {

	@Autowired
	private BankBizQueryService bankBizQueryService;

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@Override
	public Money calculateTotalLimit(BusinessSide businessSide) {
		Money result = new Money();
		BusinessSideSet businessSideSet = businessSideQueryService
				.queryBusinessSideSet(businessSide);
		BusinessSideSetType businessSideSetType = businessSideSet.getType();
		if (businessSideSetType == BusinessSideSetType.SINGLE) {
			result.setAmount(new BigDecimal("10000000"));
		} else if (businessSideSetType == BusinessSideSetType.UNITED) {
			result.setAmount(new BigDecimal("15000000"));
		} else {
			throw new CommonException("不支持的业务集合[" + businessSideSetType + "]");
		}
		return result;
	}

	@Override
	public Money calculateUsedLimit(BusinessSide businessSide) {
		Money result = new Money();
		BusinessSideSet businessSideSet = businessSideQueryService
				.queryBusinessSideSet(businessSide);
		// 计算个人业务所占额度
		result.add(calculateUsedLimitForPerson(businessSideSet.getPerson()));
		// 计算关联各企业业务所占额度
		result.add(calculateUsedLimitForEnterprise(businessSideSet
				.getEnterprises()));
		return result;
	}

	private Money calculateUsedLimitForEnterprise(List<Enterprise> enterprises) {
		if (!CollectionUtils.isEmpty(enterprises)) {
			Money result = new Money();
			for (Enterprise enterprise : enterprises) {
				BankBizType[] bankBizTypes = BankBizType.values();
				for (BankBizType type : bankBizTypes) {
					result.add(calculateDetailLimit(enterprise, type));
				}
			}
			return result;
		} else {
			return new Money();
		}
	}

	private Money calculateUsedLimitForPerson(Person person) {
		return calculateDetailLimit(person, BankBizType.FLOATING_LOAN);
	}

	@Override
	public Money calculateAvailableLimit(BusinessSide businessSide) {
		return calculateTotalLimit(businessSide).subtract(
				calculateUsedLimit(businessSide));
	}

	@Override
	public Money calculateDetailLimit(BusinessSide businessSide,
			BankBizType type) {
		Money result = new Money();
		int bizSideId = businessSide.getId();
		Date calDate = new Date();
		if (businessSide instanceof Person) {
			// 个人业务目前只有流贷
			Map<String, Object> extraParams = new HashMap<String, Object>();
			extraParams.put(Constants.LoanBizSideType, LoanBizSideType.PRIVATE);
			return calculteOccupiedMoneyForFL(
					bankBizQueryService.query(FloatingLoan.class,
							businessSide.getId(), calDate, null, extraParams),
					calDate);
		} else {
			if (type == BankBizType.FLOATING_LOAN) {
				result = calculteOccupiedMoneyForFL(bankBizQueryService.query(
						FloatingLoan.class, bizSideId, calDate, null, null),
						calDate);
			} else if (type == BankBizType.BANDAR_NOTE) {
				result = calculteOccupiedMoneyForBN(bankBizQueryService.query(
						BandarNote.class, bizSideId, calDate, null, null),
						calDate);
			} else if (type == BankBizType.DISCOUNT) {
				result = calculteOccupiedMoneyForDC(bankBizQueryService.query(
						Discount.class, bizSideId, calDate, null, null),
						calDate);
			}
		}
		return result;
	}

	private Money calculteOccupiedMoneyForFL(List<FloatingLoan> query,
			Date calDate) {
		Money result = new Money();
		if (!CollectionUtils.isEmpty(query)) {
			for (FloatingLoan loan : query) {
				if (loan.occupyLimit(calDate)) {
					result.addTo(loan.occupyMoney());
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
					result.addTo(item.occupyMoney());
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
					result.addTo(item.occupyMoney());
				}
			}
		}
		return result;
	}

	@Override
	public LimitControlResult isOverLimit(BusinessSide businessSide,
			Date calDate, Money newAmount) {
		LimitControlResult result = new LimitControlResult();
		Money total = calculateTotalLimit(businessSide);
		result.setTotal(total);
		Money available = calculateAvailableLimit(businessSide);
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
