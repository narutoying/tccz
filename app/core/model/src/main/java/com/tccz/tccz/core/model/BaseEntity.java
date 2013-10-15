/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.tccz.tccz.common.util.DateUtil;

/**
 * 基本实体
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BaseEntity.java, v 0.1 2013-9-12 上午11:33:19
 *          narutoying09@gmail.com Exp $
 */
public class BaseEntity {
	protected int id;
	/** 创建时间 */
	protected Date createTime;
	/** 修改时间 */
	protected Date modifyTime;
	protected String createTimeStr;
	protected String modifyTimeStr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeStr = DateUtil.getNewFormatDateString(createTime);
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		this.modifyTimeStr = DateUtil.getNewFormatDateString(modifyTime);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public String getModifyTimeStr() {
		return modifyTimeStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
