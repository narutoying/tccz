/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

/**
 * 额度种类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitCode.java, v 0.1 2013-9-11 上午9:22:32
 *          narutoying09@gmail.com Exp $
 */
public enum LimitType {

	/** 综合授信额度 */
	COMPOSITE_CREDIT("COMPOSITE_CREDIT", "综合授信额度"),
	/** 流动贷款占用额度 */
	FLOATING_LOAN_USED("FLOATING_LOAN_USED", "流动贷款占用额度"),
	/** 银票占用额度 */
	BANDAR_NOTE_USED("BANDAR_NOTE_USED", "银票占用额度"),
	/** 贴现占用额度 */
	DISCOUNT_USED("DISCOUNT_USED", "贴现占用额度"),
	/** 可用额度 */
	AVAILABLE("AVAILABLE", "可用额度");

	private String code;

	private LimitType(String code, String description) {
		this.code = code;
		this.description = description;
	}

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
