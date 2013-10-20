/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.ArrayList;
import java.util.List;

import com.tccz.tccz.core.model.enums.BusinessSideType;

/**
 * 个人
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Person.java, v 0.1 2013-9-11 下午4:49:38 narutoying09@gmail.com
 *          Exp $
 */
public class Person extends BusinessSide {

	/** 身份证号，作为个人唯一标识，替代id */
	private String idCardNumber;

	/** 该人作为法人代表的企业列表（一个人可用是多家企业的法人） */
	private List<Enterprise> ownEnterprises = new ArrayList<Enterprise>();

	/** 直接关联的企业，个人非企业法人代表 */
	private List<Enterprise> relationEnterprises = new ArrayList<Enterprise>();

	public List<Enterprise> getOwnEnterprises() {
		return ownEnterprises;
	}

	public void setOwnEnterprises(List<Enterprise> ownEnterprises) {
		this.ownEnterprises = ownEnterprises;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public List<Enterprise> getRelationEnterprises() {
		return relationEnterprises;
	}

	public void setRelationEnterprises(List<Enterprise> relationEnterprises) {
		this.relationEnterprises = relationEnterprises;
	}

	@Override
	public String getIdentifier() {
		return idCardNumber;
	}

	@Override
	public String setIdentifier(String identifier) {
		return this.idCardNumber = identifier;
	}

	@Override
	public String getBusinessSideType() {
		return BusinessSideType.PERSON.getCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((idCardNumber == null) ? 0 : idCardNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (idCardNumber == null) {
			if (other.idCardNumber != null)
				return false;
		} else if (!idCardNumber.equals(other.idCardNumber))
			return false;
		return true;
	}
}
