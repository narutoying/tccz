/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.person;

import com.tccz.tccz.core.model.Person;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: EnterpriseForm.java, v 0.1 2013-10-21 下午2:12:04
 *          narutoying09@gmail.com Exp $
 */
public class PersonForm {
	private String name;

	private String idCardNumber;

	private String accountNumber;

	public Person convertToDomain() {
		Person result = new Person();
		result.setAccountNumber(this.accountNumber);
		result.setName(name);
		result.setIdentifier(idCardNumber);
		result.setIdCardNumber(idCardNumber);
		result.setName(name);
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
