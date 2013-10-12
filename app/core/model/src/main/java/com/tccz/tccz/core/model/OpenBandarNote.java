/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import com.tccz.tccz.common.util.exception.CommonException;

/**
 * 敞口银票
 * 
 * @author narutoying09@gmail.com
 * @version $Id: OpenBandarNote.java, v 0.1 2013-9-12 上午11:44:34
 *          narutoying09@gmail.com Exp $
 */
public class OpenBandarNote extends BandarNote {
	/** 敞口金额 */
	private Money openMoney = new Money();
	/** 封敞口金额 */
	private Money closeMoney = new Money();

	public Money getOpenMoney() {
		return openMoney;
	}

	public void setOpenMoney(Money openMoney) {
		this.openMoney = openMoney;
	}

	public Money getCloseMoney() {
		return closeMoney;
	}

	public void setCloseMoney(Money closeMoney) {
		this.closeMoney = closeMoney;
	}

	@Override
	public Money occupyMoney() {
		return openMoney.subtract(closeMoney);
	}

	@Override
	public void verifyData() {
		if (!amount.equals(margin.add(openMoney))) {
			throw new CommonException("银票金额不等于保证金与敞口金额之和");
		}
		Money subtract = openMoney.subtract(closeMoney);
		if (subtract.getCent() < 0) {
			throw new CommonException("封敞口金额大于敞口金额，数据异常");
		}
	}
}
