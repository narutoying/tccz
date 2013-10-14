/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.query;

import java.util.Date;

import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.enums.LoanBizSideType;

/**
 * 流贷查询条件
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanQueryCondition.java, v 0.1 2013-10-12 下午2:35:40
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanQueryCondition {
	/** 企业名称 */
	private String loanerName;
	/** 业务方类型 */
	private String bizSideType = LoanBizSideType.CORPORATE.toString();
	private boolean showExpire = false;
	/** 过期日期查询起始 */
	private Date expireStart;
	/** 过期日期查询结束 */
	private Date expireEnd;
	/** 单页记录数目 */
	private Integer limit = PageUtil.PAGE_SIZE;
	/** 当前页 */
	private Integer page = 1;

	public String getLoanerName() {
		return loanerName;
	}

	public void setLoanerName(String loanerName) {
		this.loanerName = loanerName;
	}

	public String getBizSideType() {
		return bizSideType;
	}

	public void setBizSideType(String bizSideType) {
		this.bizSideType = bizSideType;
	}

	public boolean isShowExpire() {
		return showExpire;
	}

	public void setShowExpire(boolean showExpire) {
		this.showExpire = showExpire;
	}

	public Date getExpireStart() {
		return expireStart;
	}

	public void setExpireStart(Date expireStart) {
		this.expireStart = expireStart;
	}

	public Date getExpireEnd() {
		return expireEnd;
	}

	public void setExpireEnd(Date expireEnd) {
		this.expireEnd = expireEnd;
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
