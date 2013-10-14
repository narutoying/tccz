/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

/**
 * 银行业务类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BankBizType.java, v 0.1 2013-10-14 上午10:06:26
 *          narutoying09@gmail.com Exp $
 */
public enum BankBizType {

	/** 流动贷款 */
	FLOATING_LOAN("FLOATING_LOAN", "流动贷款"),
	/** 银票 */
	BANDAR_NOTE("BANDAR_NOTE", "银票"),
	/** 贴现 */
	DISCOUNT("DISCOUNT", "贴现");

	private BankBizType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	private String code;

	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
