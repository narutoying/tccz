/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.ArrayList;
import java.util.List;

import com.tccz.tccz.core.model.enums.BusinessSideSetType;

/**
 * 业务方集合，由个人、企业构成
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideUnit.java, v 0.1 2013-10-14 下午4:19:17
 *          narutoying09@gmail.com Exp $
 */
public class BusinessSideSet {
	/** 集合类型 */
	private BusinessSideSetType type;
	/** 关联个人 */
	private List<Person> persons = new ArrayList<Person>();
	/** 关联企业 */
	private List<Enterprise> enterprises = new ArrayList<Enterprise>();

	/**
	 * 整合进其他的集合
	 * 
	 * @param set
	 */
	public void combine(BusinessSideSet set) {
		if (set != null) {
			this.persons.addAll(set.getPersons());
			this.enterprises.addAll(set.getEnterprises());
		}
	}

	public BusinessSideSetType getType() {
		return type;
	}

	public void setType(BusinessSideSetType type) {
		this.type = type;
	}

	public List<Enterprise> getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(List<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
