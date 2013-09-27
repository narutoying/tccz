/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.result;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.tccz.tccz.core.model.Money;

/**
 * 额度控制结果
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitCalculateResult.java, v 0.1 2013-9-13 下午3:35:50
 *          narutoying09@gmail.com Exp $
 */
public class LimitControlResult {
	/** 是否超过额度 */
	private boolean isOverLimit;
	/** 总额度 */
	private Money total;
	/** 当前可用额度 */
	private Money available;
	/** 参与比较的金额 */
	private Money compareAmount;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getResultString() {
		return "提交金额(" + compareAmount.toString() + ")元，"
				+ (isOverLimit == true ? "超过" : "") + "可用额度为(" + available
				+ ")元";
	}

	public boolean isOverLimit() {
		return isOverLimit;
	}

	public void setOverLimit(boolean isOverLimit) {
		this.isOverLimit = isOverLimit;
	}

	public Money getTotal() {
		return total;
	}

	public void setTotal(Money total) {
		this.total = total;
	}

	public Money getAvailable() {
		return available;
	}

	public void setAvailable(Money available) {
		this.available = available;
	}

	public Money getCompareAmount() {
		return compareAmount;
	}

	public void setCompareAmount(Money compareAmount) {
		this.compareAmount = compareAmount;
	}
}
