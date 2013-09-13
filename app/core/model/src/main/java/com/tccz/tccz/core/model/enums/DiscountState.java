/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

/**
 * 贴现状态
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountState.java, v 0.1 2013-9-12 下午1:55:08
 *          narutoying09@gmail.com Exp $
 */
public enum DiscountState {
	/** 在库 */
	IN_STORE("IN_STORE", "在库"),
	/** 转贴现 */
	TRANSFER("TRANSFER", "转贴现"),
	/** 再贴现 */
	RE_DISCOUNT("RE_DISCOUNT", "再贴现"),
	/** 已回购 */
	BUY_BACK("BUY_BACK", "已回购"),
	/** 已托收 */
	COLLECTED("COLLECTED", "已托收");

	private DiscountState(String code, String description) {
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