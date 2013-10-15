/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.biz.model;

import java.util.ArrayList;
import java.util.List;

import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.BusinessSideSetType;

/**
 * 企业风险额度视图
 * 
 * @author narutoying09@gmail.com
 * @version $Id: EnterpriseLimitView.java, v 0.1 2013-10-15 上午9:19:32
 *          narutoying09@gmail.com Exp $
 */
public class EnterpriseLimitView {
	/** 企业 */
	private Enterprise enterprise;
	/** 法人 */
	private Person legalPerson;
	/** 所属业务方集合类型 */
	private BusinessSideSetType businessSideSetType;
	/** 关联企业 */
	private List<Enterprise> associateEnterprises = new ArrayList<Enterprise>();
	/** 总额度 */
	private Money totalLimit;
	/** 可用额度 */
	private Money availableLimit;

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Person getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(Person legalPerson) {
		this.legalPerson = legalPerson;
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
}
