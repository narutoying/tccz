/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import com.tccz.tccz.core.model.enums.LimitType;

/**
 * 额度
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Limit.java, v 0.1 2013-9-11 上午9:18:08 narutoying09@gmail.com
 *          Exp $
 */
public class Limit {

	/** 额度类型 */
	private LimitType limitType;

	/** 金额 */
	private Money money;

	public LimitType getLimitType() {
		return limitType;
	}

	public void setLimitType(LimitType limitType) {
		this.limitType = limitType;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return limitType.getCode() + ", " + money.toString();
	}
}
