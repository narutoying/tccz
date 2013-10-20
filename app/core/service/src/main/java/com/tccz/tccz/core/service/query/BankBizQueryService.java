/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

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
	/**
	 * 按照到期时间查询业务方的特定业务数据 （如查询某家企业到期时间在2013-01-01到2013-12-31内的流贷数据）
	 * 
	 * @param classType
	 *            业务种类
	 * @param bizSideId
	 *            业务方id，如某企业id
	 * @param expireDateStart
	 *            到期时间查询开始段（闭区间）
	 * @param expireDateEnd
	 *            到期时间查询结束段（闭区间）
	 * @param extraParams
	 *            其他参数
	 * @return
	 */
	<T> List<T> query(Class<T> classType, String bizSideId,
			Date expireDateStart, Date expireDateEnd,
			Map<String, Object> extraParams);
}
