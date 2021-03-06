/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.Date;

import com.tccz.tccz.core.model.enums.LoanBizSideType;

/**
 * 流贷
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoan.java, v 0.1 2013-9-11 下午5:06:15
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoan extends BaseBankBusinessEntity {
	/** 贷款方 */
	private BusinessSide loaner;
	/** 贷款业务方类型 */
	private LoanBizSideType bizSideType;
	/** 贷款金额（当前余额） */
	private Money amount;
	/** 发放日 */
	private Date releaseDate;
	/** 已还款 */
	private boolean hasRepayed;

	public BusinessSide getLoaner() {
		return loaner;
	}

	public void setLoaner(BusinessSide loaner) {
		this.loaner = loaner;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public Money occupyMoney() {
		return this.amount;
	}

	public LoanBizSideType getBizSideType() {
		return bizSideType;
	}

	public void setBizSideType(LoanBizSideType bizSideType) {
		this.bizSideType = bizSideType;
	}

	public boolean isHasRepayed() {
		return hasRepayed;
	}

	public void setHasRepayed(boolean hasRepayed) {
		this.hasRepayed = hasRepayed;
	}

	/**
	 * 流贷比较特殊
	 * 
	 * @see com.tccz.tccz.core.model.BaseBankBusinessEntity#occupyLimit(java.util.Date)
	 */
	@Override
	public boolean occupyLimit(Date compareDate) {
		// 流贷仅在“已还款”才会释放所占额度，无论到期与否
		if (!this.hasRepayed) {
			return true;
		} else {
			return false;
		}
	}

}
