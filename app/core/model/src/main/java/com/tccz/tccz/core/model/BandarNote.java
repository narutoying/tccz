/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.Date;

import com.tccz.tccz.core.model.enums.BandarNoteType;

/**
 * 银票
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNote.java, v 0.1 2013-9-11 下午5:06:44
 *          narutoying09@gmail.com Exp $
 */
public class BandarNote extends BaseBankBusinessEntity {
	/** 银票号 */
	private String number;
	/** 出票方 */
	private Enterprise drawer;
	/** 银票类型 */
	private BandarNoteType type;
	/** 银票金额 */
	private Money amount;
	/** 出票日 */
	private Date drawDate;
	/** 保证金 */
	private Money margin;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BandarNoteType getType() {
		return type;
	}

	public void setType(BandarNoteType type) {
		this.type = type;
	}

	public Date getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}

	public Money getMargin() {
		return margin;
	}

	public void setMargin(Money margin) {
		this.margin = margin;
	}

	public Enterprise getDrawer() {
		return drawer;
	}

	public void setDrawer(Enterprise drawer) {
		this.drawer = drawer;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

}
