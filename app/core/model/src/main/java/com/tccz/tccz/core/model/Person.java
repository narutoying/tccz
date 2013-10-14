/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.List;

/**
 * 个人
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Person.java, v 0.1 2013-9-11 下午4:49:38 narutoying09@gmail.com
 *          Exp $
 */
public class Person extends BusinessSide {
	/*
	 * 基本属性
	 */
	/** 该人作为法人代表的企业列表（一个人可用是多家企业的法人） */
	private List<Enterprise> ownEnterprises;
	/*
	 * 非基本属性
	 */
	/** 总额度 */
	private Limit wholeLimit;
	/** 可用额度 */
	private Limit availableLimit;
	/** 流贷已占用额度 */
	private Limit floatingLoanUseLimit;

	public Limit getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(Limit availableLimit) {
		this.availableLimit = availableLimit;
	}

	public List<Enterprise> getOwnEnterprises() {
		return ownEnterprises;
	}

	public void setOwnEnterprises(List<Enterprise> ownEnterprises) {
		this.ownEnterprises = ownEnterprises;
	}

	public Limit getWholeLimit() {
		return wholeLimit;
	}

	public void setWholeLimit(Limit wholeLimit) {
		this.wholeLimit = wholeLimit;
	}

	public Limit getFloatingLoanUseLimit() {
		return floatingLoanUseLimit;
	}

	public void setFloatingLoanUseLimit(Limit floatingLoanUseLimit) {
		this.floatingLoanUseLimit = floatingLoanUseLimit;
	}
}
