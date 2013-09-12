/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service;

import java.util.Date;

import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Limit;
import com.tccz.tccz.core.model.enums.LimitType;

/**
 * 额度核心服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitService.java, v 0.1 2013-9-12 下午2:22:12
 *          narutoying09@gmail.com Exp $
 */
public interface LimitService {
	/**
	 * 计算额度
	 * 
	 * @param businessSide
	 *            业务方
	 * @param calDate
	 *            计算日期 本次计算会以该日期作为基准，精确到天。
	 * @param limitType 计算额度的种类
	 * @return
	 */
	Limit calculateLimit(BusinessSide businessSide, Date calDate,
			LimitType limitType);
}
