/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.bandarnote;

import java.util.Date;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountForm.java, v 0.1 2013-9-23 上午8:37:14
 *          narutoying09@gmail.com Exp $
 */
public class BandarNoteForm {

	private Integer id;

	private String bandarNoteNumber;

	private String type;

	private int drawerId;

	private String amount;

	private Date drawDate;

	private Date expireDate;

	private String marginMoney;

	private String openMoney;

	private String closeMoney;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBandarNoteNumber() {
		return bandarNoteNumber;
	}

	public void setBandarNoteNumber(String bandarNoteNumber) {
		this.bandarNoteNumber = bandarNoteNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDrawerId() {
		return drawerId;
	}

	public void setDrawerId(int drawerId) {
		this.drawerId = drawerId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getMarginMoney() {
		return marginMoney;
	}

	public void setMarginMoney(String marginMoney) {
		this.marginMoney = marginMoney;
	}

	public String getOpenMoney() {
		return openMoney;
	}

	public void setOpenMoney(String openMoney) {
		this.openMoney = openMoney;
	}

	public String getCloseMoney() {
		return closeMoney;
	}

	public void setCloseMoney(String closeMoney) {
		this.closeMoney = closeMoney;
	}

}
