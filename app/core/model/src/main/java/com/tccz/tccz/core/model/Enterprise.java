/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.ArrayList;
import java.util.List;

import com.tccz.tccz.core.model.enums.BusinessSideType;

/**
 * 企业
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Enterprise.java, v 0.1 2013-9-11 下午4:42:43
 *          narutoying09@gmail.com Exp $
 */
public class Enterprise extends BusinessSide {

	/** 机构代码，唯一标识一家企业，替代id */
	private String institutionCode;

	/** 企业法人，一家企业有且仅有一个法人 */
	private Person legalPerson;

	/** 与企业直接关联的非法人 */
	private List<Person> relationPersons = new ArrayList<Person>();

	public Enterprise() {
		super();
	}

	public Enterprise(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Person getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(Person legalPerson) {
		this.legalPerson = legalPerson;
	}

	public List<Person> getRelationPersons() {
		return relationPersons;
	}

	public void setRelationPersons(List<Person> relationPersons) {
		this.relationPersons = relationPersons;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	@Override
	public String getIdentifier() {
		return institutionCode;
	}

	@Override
	public String setIdentifier(String identifier) {
		return this.institutionCode = identifier;
	}

	@Override
	public String getBusinessSideType() {
		return BusinessSideType.ENTERPRISE.getCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((institutionCode == null) ? 0 : institutionCode.hashCode());
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
		Enterprise other = (Enterprise) obj;
		if (institutionCode == null) {
			if (other.institutionCode != null)
				return false;
		} else if (!institutionCode.equals(other.institutionCode))
			return false;
		return true;
	}

}
