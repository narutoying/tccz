/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.interfaces;

import java.util.Date;

/**
 * 额度判断
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitDecider.java, v 0.1 2013-9-12 下午3:01:13
 *          narutoying09@gmail.com Exp $
 */
public interface LimitDecider {
	/**
	 * 判断是否占用额度
	 * 
	 * @param compareDate
	 *            比较基准日期
	 * @return
	 */
	boolean occupyLimit(Date compareDate);
}
