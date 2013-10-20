/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

import java.util.Date;
import java.util.List;

import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.query.FloatingLoanQueryCondition;

/**
 * 流贷查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanQueryService.java, v 0.1 2013-9-30 下午4:28:05
 *          narutoying09@gmail.com Exp $
 */
public interface FloatingLoanQueryService {
	/**
	 * 条件查询流贷列表
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 */
	PageList<FloatingLoan> queryByCondition(FloatingLoanQueryCondition condition);

	/**
	 * 查询指定ID的流贷
	 * 
	 * @param itemId
	 * @return
	 */
	FloatingLoan queryById(int itemId);

	/**
	 * 以到期日期查询流贷列表
	 * 
	 * @param bizSideId
	 * @param expireStart
	 * @param expireEnd
	 * @return
	 */
	List<FloatingLoan> queryListByExpireDate(String bizSideId,
			String bizSideType, Date expireStart, Date expireEnd);
}
