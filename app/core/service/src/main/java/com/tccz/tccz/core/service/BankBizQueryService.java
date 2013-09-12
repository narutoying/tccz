/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 银行业务查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BankBizQueryService.java, v 0.1 2013-9-12 下午4:22:36
 *          narutoying09@gmail.com Exp $
 */
public interface BankBizQueryService {
	<T> List<T> query(Class<T> classType, Date expireDateStart,
			Date expireDateEnd, Map<String, Object> extraParams);
}
