/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.floatingloan;

import java.util.Date;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountForm.java, v 0.1 2013-9-23 上午8:37:14
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanForm {
	private Integer id;
	private String bizSideType;
	private int loanerId;
	private String amount;
	private Date releaseDate;
	private Date expireDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBizSideType() {
		return bizSideType;
	}

	public void setBizSideType(String bizSideType) {
		this.bizSideType = bizSideType;
	}

	public int getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(int loanerId) {
		this.loanerId = loanerId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
