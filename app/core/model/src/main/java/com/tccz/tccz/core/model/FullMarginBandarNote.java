/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model;

import com.tccz.tccz.common.util.exception.CommonException;

/**
 * 全额保证金银票
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FullMarginBandarNote.java, v 0.1 2013-10-10 上午9:34:40
 *          narutoying09@gmail.com Exp $
 */
public class FullMarginBandarNote extends BandarNote {
	@Override
	public Money occupyMoney() {
		return new Money();
	}

	@Override
	public void verifyData() {
		if (!amount.equals(margin)) {
			throw new CommonException("保证金与银票金额一致");
		}
	}
}
