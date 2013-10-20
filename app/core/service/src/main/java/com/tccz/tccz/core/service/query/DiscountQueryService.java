/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

import java.util.Date;
import java.util.List;

import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.DiscountChange;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;

/**
 * 贴现查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountQueryService.java, v 0.1 2013-9-13 下午4:31:19
 *          narutoying09@gmail.com Exp $
 */
public interface DiscountQueryService {

	/**
	 * 条件查询贴现列表
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 */
	PageList<Discount> queryByCondition(DiscountQueryCondition condition);

	/**
	 * 查询指定id的贴现
	 * 
	 * @param discountId
	 * @return
	 */
	Discount queryById(int discountId);

	/**
	 * 查询贴现变更历史
	 * 
	 * @param discountId
	 * @return
	 */
	List<DiscountChange> queryDiscountChanges(int discountId);

	/**
	 * 查询
	 * 
	 * @param bizSideId
	 * @param expireStart
	 * @param expireEnd
	 * @return
	 */
	List<Discount> queryDiscounts(String bizSideId, Date expireStart,
			Date expireEnd);

}
