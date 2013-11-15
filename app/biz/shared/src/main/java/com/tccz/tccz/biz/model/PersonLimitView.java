/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.biz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.BusinessSideSetType;

/**
 * 个人额度总览
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PersonLimitView.java, v 0.1 2013-10-15 上午9:25:27
 *          narutoying09@gmail.com Exp $
 */
public class PersonLimitView {
	/** 个人 */
	private Person person;
	/** 所属业务方集合类型 */
	private BusinessSideSetType businessSideSetType;
	/** 法人企业 */
	private List<Enterprise> ownEnterprises = new ArrayList<Enterprise>();
	/** 关联企业（非法人） */
	private List<Enterprise> associateEnterprises = new ArrayList<Enterprise>();
	/** 关联个人（不含本人） */
	private List<Person> associatePersons = new ArrayList<Person>();
	/** 总额度 */
	private Money totalLimit;
	/** 可用额度 */
	private Money availableLimit;
	/** 额度占用明细，如流贷业务 */
	private Map<String, Money> detailOccupyLimit;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public BusinessSideSetType getBusinessSideSetType() {
		return businessSideSetType;
	}

	public void setBusinessSideSetType(BusinessSideSetType businessSideSetType) {
		this.businessSideSetType = businessSideSetType;
	}

	public List<Enterprise> getAssociateEnterprises() {
		return associateEnterprises;
	}

	public void setAssociateEnterprises(List<Enterprise> associateEnterprises) {
		this.associateEnterprises = associateEnterprises;
	}

	public Money getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(Money totalLimit) {
		this.totalLimit = totalLimit;
	}

	public Money getAvailableLimit() {
		return availableLimit;
	}

	public void setAvailableLimit(Money availableLimit) {
		this.availableLimit = availableLimit;
	}

	public List<Person> getAssociatePersons() {
		return associatePersons;
	}

	public void setAssociatePersons(List<Person> associatePersons) {
		this.associatePersons = associatePersons;
	}

	public List<Enterprise> getOwnEnterprises() {
		return ownEnterprises;
	}

	public void setOwnEnterprises(List<Enterprise> ownEnterprises) {
		this.ownEnterprises = ownEnterprises;
	}

	public Map<String, Money> getDetailOccupyLimit() {
		return detailOccupyLimit;
	}

	public void setDetailOccupyLimit(Map<String, Money> detailOccupyLimit) {
		this.detailOccupyLimit = detailOccupyLimit;
	}
}
