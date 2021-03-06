/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.Date;
import java.util.List;

import net.sf.json.JSON;

import com.tccz.tccz.core.model.enums.DiscountState;

/**
 * 贴现
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Discount.java, v 0.1 2013-9-11 下午5:06:59 narutoying09@gmail.com
 *          Exp $
 */
public class Discount extends BaseBankBusinessEntity {
	/** 银票号 */
	private String bandarNoteNumber;
	/** 申请人 */
	private Enterprise proposer;
	/** 金额 */
	private Money amount;
	/**
	 * 状态(需要特别注意状态与到期日期的校验)
	 */
	private DiscountState state;
	/*
	 * 非基本属性
	 */
	private List<DiscountChange> changeHistory;
	private JSON changeHistoryJson;

	@Override
	public boolean occupyLimit(Date compareDate) {
		if (state == DiscountState.TRANSFER || state == DiscountState.COLLECTED) {
			return false;
		} else {
			return super.occupyLimit(compareDate);
		}
	}

	public String getBandarNoteNumber() {
		return bandarNoteNumber;
	}

	public void setBandarNoteNumber(String bandarNoteNumber) {
		this.bandarNoteNumber = bandarNoteNumber;
	}

	public Enterprise getProposer() {
		return proposer;
	}

	public void setProposer(Enterprise proposer) {
		this.proposer = proposer;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

	public DiscountState getState() {
		return state;
	}

	public void setState(DiscountState state) {
		this.state = state;
	}

	public List<DiscountChange> getChangeHistory() {
		return changeHistory;
	}

	public void setChangeHistory(List<DiscountChange> changeHistory) {
		this.changeHistory = changeHistory;
	}

	public JSON getChangeHistoryJson() {
		return changeHistoryJson;
	}

	public void setChangeHistoryJson(JSON changeHistoryJson) {
		this.changeHistoryJson = changeHistoryJson;
	}

	@Override
	public Money occupyMoney() {
		return this.amount;
	}
}
