/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.discount;

import java.util.ArrayList;
import java.util.List;

/**
 * 贴现分页查询结果
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountResultForm.java, v 0.1 2013-9-20 上午12:36:21
 *          narutoying09@gmail.com Exp $
 */
public class DiscountPageResultForm {
	/** 一页数据数量 */
	private Integer pageSize;
	/** 当前页 */
	private Integer pageNum;
	/** 总记录数 */
	private Integer totalCount;
	/** 贴现数据项 */
	private List<DiscountPageItem> items = new ArrayList<DiscountPageItem>();

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public List<DiscountPageItem> getItems() {
		return items;
	}

	public void setItems(List<DiscountPageItem> items) {
		this.items = items;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}