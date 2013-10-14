/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.floatingloan;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountPageItem.java, v 0.1 2013-9-20 上午12:55:24
 *          narutoying09@gmail.com Exp $
 */
public class PageItem {
	private int id;
	/** 贷款企业/人名 */
	private String loanerName;
	/** 贷款类型（业务方） */
	private String bizSideType;
	/** 金额 */
	private String amount;
	/** 发放日 */
	private String releaseDate;
	/** 到期日期 */
	private String expireDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoanerName() {
		return loanerName;
	}

	public void setLoanerName(String loanerName) {
		this.loanerName = loanerName;
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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
}
