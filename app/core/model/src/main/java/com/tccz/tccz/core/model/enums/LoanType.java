/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

/**
 * 贷款类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LoanType.java, v 0.1 2013-9-12 上午11:21:12
 *          narutoying09@gmail.com Exp $
 */
public enum LoanType {
	/** 对公 */
	CORPORATE("CORPORATE", "对公"),
	/** 对私 */
	PRIVATE("PRIVATE", "个人");
	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private LoanType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
