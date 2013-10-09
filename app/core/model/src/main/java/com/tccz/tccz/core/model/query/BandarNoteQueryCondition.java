/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.query;

import com.tccz.tccz.common.util.PageUtil;

/**
 * 银票查询条件
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteQueryCondition.java, v 0.1 2013-9-30 下午4:36:09
 *          narutoying09@gmail.com Exp $
 */
public class BandarNoteQueryCondition {
	/** 企业名称 */
	private String enterpriseName;
	/** 银票号 */
	private String number;
	/** 银票类型 */
	private String type;
	/** 是否显示过期条目 */
	private boolean showExpire = false;
	/** 单页记录数目 */
	private Integer limit = PageUtil.PAGE_SIZE;
	/** 当前页 */
	private Integer page = 1;

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isShowExpire() {
		return showExpire;
	}

	public void setShowExpire(boolean showExpire) {
		this.showExpire = showExpire;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
