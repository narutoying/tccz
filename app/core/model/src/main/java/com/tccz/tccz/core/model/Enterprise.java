/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

/**
 * 企业
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Enterprise.java, v 0.1 2013-9-11 下午4:42:43
 *          narutoying09@gmail.com Exp $
 */
public class Enterprise extends BusinessSide {

	public Enterprise() {
		super();
	}

	public Enterprise(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/*
	 * 基本属性
	 */
	/** 企业法人 */
	private Person legalPerson;
	/*
	 * 非基本属性
	 */
	/** 总额度 */
	private Limit wholeLimit;
	/** 可用额度 */
	private Limit availableLimit;
	/** 流贷已占用额度 */
	private Limit floatingLoanUseLimit;
	/** 银票已占用额度 */
	private Limit bandarNoteUseLimit;
	/** 贴现已占用额度 */
	private Limit discountUseLimit;

	public Limit getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(Limit availableLimit) {
		this.availableLimit = availableLimit;
	}

	public Person getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(Person legalPerson) {
		this.legalPerson = legalPerson;
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

	public Limit getBandarNoteUseLimit() {
		return bandarNoteUseLimit;
	}

	public void setBandarNoteUseLimit(Limit bandarNoteUseLimit) {
		this.bandarNoteUseLimit = bandarNoteUseLimit;
	}

	public Limit getDiscountUseLimit() {
		return discountUseLimit;
	}

	public void setDiscountUseLimit(Limit discountUseLimit) {
		this.discountUseLimit = discountUseLimit;
	}

}
