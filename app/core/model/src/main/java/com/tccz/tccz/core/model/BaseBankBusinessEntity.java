/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import java.util.Date;

import com.tccz.tccz.core.model.interfaces.LimitDecider;

/**
 * 银行业务基本实体
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BaseBusinessEntity.java, v 0.1 2013-9-12 下午2:51:41
 *          narutoying09@gmail.com Exp $
 */
public class BaseBankBusinessEntity extends BaseEntity implements LimitDecider {

	/** 到期日期 */
	protected Date expireDate;

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 默认判断策略是到期日期A和比较日期B相比，若A>=B，则表示占用；反之，则不占用。 
	 * 各业务实体可决定自己的判断策略。
	 * 
	 * @see com.tccz.tccz.core.model.interfaces.LimitDecider#occupyLimit(java.util.Date)
	 */
	@Override
	public boolean occupyLimit(Date compareDate) {
		String DATE_FORMAT = "yyyy-MM-dd";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				DATE_FORMAT);
		String date1Str = sdf.format(expireDate);
		String date2Str = sdf.format(compareDate);
		if (date1Str.equals(date2Str)) {
			return true;
		} else {
			if (expireDate.compareTo(compareDate) < 0) {
				return false;
			} else {
				return true;
			}
		}
	}
}
