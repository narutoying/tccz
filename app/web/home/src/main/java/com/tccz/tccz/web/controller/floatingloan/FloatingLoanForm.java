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
	private String loanerId;
	private String amount;
	private Date releaseDate;
	private Date expireDate;
	private boolean hasRepayed;

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

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public boolean isHasRepayed() {
		return hasRepayed;
	}

	public void setHasRepayed(boolean hasRepayed) {
		this.hasRepayed = hasRepayed;
	}
}
