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
	/** 个人 */
	private Person person;
	/** 个人（法人）所关联的企业 */
	private List<Enterprise> enterprises = new ArrayList<Enterprise>();

	public BusinessSideSetType getType() {
		return type;
	}

	public void setType(BusinessSideSetType type) {
		this.type = type;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Enterprise> getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(List<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}
}
