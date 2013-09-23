/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.enums;

/**
 * 操作类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: OperationType.java, v 0.1 2013-9-23 上午10:21:32
 *          narutoying09@gmail.com Exp $
 */
public enum OperationType {

	ADD("ADD", "新增"), DELETE("DELETE", "删除"), UPDATE("UPDATE", "修改"), QUERY(
			"QUERY", "查看");

	public static final String OPERATION = "operation";
	public static final String OPERATION_DESC = "operationDesc";

	private OperationType(String code, String description) {
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
