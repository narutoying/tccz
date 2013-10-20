/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

/**
 * 业务方，参与某项业务的自然人、企业等
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSide.java, v 0.1 2013-9-12 上午10:51:27
 *          narutoying09@gmail.com Exp $
 */
public abstract class BusinessSide extends BaseEntity {

	public abstract String getIdentifier();

	public abstract String setIdentifier(String identifier);

	public abstract String getBusinessSideType();

	/** 企业名 */
	protected String name;
	/** 账户号 */
	protected String accountNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
