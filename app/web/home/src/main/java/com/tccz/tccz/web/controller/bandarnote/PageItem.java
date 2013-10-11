/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.bandarnote;


/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountPageItem.java, v 0.1 2013-9-20 上午12:55:24
 *          narutoying09@gmail.com Exp $
 */
public class PageItem {
	private int id;
	/** 银票号 */
	private String bandarNoteNumber;
	/** 出票方 */
	private String drawer;
	/** 金额 */
	private String amount;
	/** 到期日期 */
	private String expireDate;
	/** 银票类型 */
	private String type;
	/** 出票日 */
	private String drawDate;

	public String getBandarNoteNumber() {
		return bandarNoteNumber;
	}

	public void setBandarNoteNumber(String bandarNoteNumber) {
		this.bandarNoteNumber = bandarNoteNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate;
	}

}
