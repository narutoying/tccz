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
	/** 流动贷款额度 */
	FLOATING_LOAN("FLOATING_LOAN", "流动贷款额度"),
	/** 银票额度 */
	BANDAR_NOTE("BANDAR_NOTE", "银票额度"),
	/** 贴现额度 */
	DISCOUNT("DISCOUNT", "贴现额度"),
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
