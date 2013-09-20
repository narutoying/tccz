/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.query;

import com.tccz.tccz.common.util.PageUtil;

/**
 * 贴现查询条件
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountQueryCondition.java, v 0.1 2013-9-13 下午4:34:02
 *          narutoying09@gmail.com Exp $
 */
public class DiscountQueryCondition {

	/** 企业名称 */
	private String enterpriseName;
	/** 银票号 */
	private String bandarNoteNumber;
	/** 是否显示过期贴现 */
	private boolean showExpire = false;
	/*
	 * 分页参数，若不分页，则这些参数不起作用
	 */
	private boolean doPage = true;
	private Integer limit = PageUtil.PAGE_SIZE;
	private Integer page = 1;

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getBandarNoteNumber() {
		return bandarNoteNumber;
	}

	public void setBandarNoteNumber(String bandarNoteNumber) {
		this.bandarNoteNumber = bandarNoteNumber;
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

	public boolean isDoPage() {
		return doPage;
	}

	public void setDoPage(boolean doPage) {
		this.doPage = doPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
}
