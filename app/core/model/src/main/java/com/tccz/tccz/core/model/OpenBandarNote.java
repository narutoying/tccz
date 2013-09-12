/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

/**
 * 敞口银票
 * 
 * @author narutoying09@gmail.com
 * @version $Id: OpenBandarNote.java, v 0.1 2013-9-12 上午11:44:34
 *          narutoying09@gmail.com Exp $
 */
public class OpenBandarNote extends BandarNote {
	/** 敞口金额 */
	private Money openMoney;
	/** 封敞口金额 */
	private Money closeMoney;

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
}
