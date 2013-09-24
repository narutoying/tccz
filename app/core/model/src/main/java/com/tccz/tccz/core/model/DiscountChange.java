/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import com.tccz.tccz.core.model.enums.DiscountState;

/**
 * 贴现变更，每次贴现的修改均会产生一个新的变更对象，可用于查看贴现变更历史
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountChange.java, v 0.1 2013-9-11 下午5:13:08
 *          narutoying09@gmail.com Exp $
 */
public class DiscountChange extends BaseEntity {
	/** 所属贴现 */
	private int discountId;
	/** 贴现变更状态 */
	private DiscountState state;
	private String stateDesc;

	public DiscountState getState() {
		return state;
	}

	public void setState(DiscountState state) {
		this.state = state;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
	}

	public String getStateDesc() {
		return state.getDescription();
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
}
