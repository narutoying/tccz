/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

/**
 * 银票类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteType.java, v 0.1 2013-9-12 上午11:21:12
 *          narutoying09@gmail.com Exp $
 */
public enum BandarNoteType {
	/** 全额保证金 */
	FULL_MARGIN("FULL_MARGIN", "全额保证金"),
	/** 敞口 */
	OPEN("OPEN", "敞口");
	private BandarNoteType(String code, String description) {
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
