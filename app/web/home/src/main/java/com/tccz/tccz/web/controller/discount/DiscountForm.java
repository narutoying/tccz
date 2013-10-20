/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.discount;

import java.util.Date;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountForm.java, v 0.1 2013-9-23 上午8:37:14
 *          narutoying09@gmail.com Exp $
 */
public class DiscountForm {

	private Integer id;

	private String bandarNoteNumber;

	private String proposerId;

	private String amount;

	private String state;

	private Date expireDate;

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getProposerId() {
		return proposerId;
	}

	public void setProposerId(String proposerId) {
		this.proposerId = proposerId;
	}

}
