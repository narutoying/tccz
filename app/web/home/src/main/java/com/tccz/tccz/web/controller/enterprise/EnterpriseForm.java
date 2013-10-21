/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.enterprise;

import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: EnterpriseForm.java, v 0.1 2013-10-21 下午2:12:04
 *          narutoying09@gmail.com Exp $
 */
public class EnterpriseForm {
	private String name;

	private String institutionCode;

	private String accountNumber;

	private String legalPersonId;

	public Enterprise convertToDomain() {
		Enterprise result = new Enterprise();
		result.setAccountNumber(this.accountNumber);
		result.setIdentifier(this.institutionCode);
		result.setInstitutionCode(this.institutionCode);
		Person legalPerson = new Person();
		legalPerson.setIdentifier(this.legalPersonId);
		result.setLegalPerson(legalPerson);
		result.setName(name);
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLegalPersonId() {
		return legalPersonId;
	}

	public void setLegalPersonId(String legalPersonId) {
		this.legalPersonId = legalPersonId;
	}
}
