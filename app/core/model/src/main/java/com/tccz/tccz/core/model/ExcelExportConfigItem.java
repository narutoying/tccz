/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ExcelExportConfigItem.java, v 0.1 2013-12-17 下午4:24:56
 *          narutoying09@gmail.com Exp $
 */
public class ExcelExportConfigItem {

	public ExcelExportConfigItem(String excelColName, String domainFieldName) {
		super();
		this.excelColName = excelColName;
		this.domainFieldName = domainFieldName;
	}

	/** 对应导出excel中的列名 */
	private String excelColName;
	/** 对应领域模型中的字段 */
	private String domainFieldName;

	public String getExcelColName() {
		return excelColName;
	}

	public void setExcelColName(String excelColName) {
		this.excelColName = excelColName;
	}

	public String getDomainFieldName() {
		return domainFieldName;
	}

	public void setDomainFieldName(String domainFieldName) {
		this.domainFieldName = domainFieldName;
	}
}
