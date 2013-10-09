/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

import java.util.Date;
import java.util.List;

import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.query.BandarNoteQueryCondition;

/**
 * 银票查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteQueryService.java, v 0.1 2013-9-30 下午4:28:05
 *          narutoying09@gmail.com Exp $
 */
public interface BandarNoteQueryService {
	/**
	 * 条件查询银票列表
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 */
	PageList<BandarNote> queryByCondition(BandarNoteQueryCondition condition);

	/**
	 * 查询指定ID的银票
	 * 
	 * @param itemId
	 * @return
	 */
	BandarNote queryById(int itemId);

	/**
	 * 以到期日期查询银票列表
	 * 
	 * @param bizSideId
	 * @param expireStart
	 * @param expireEnd
	 * @return
	 */
	List<BandarNote> queryListByExpireDate(int bizSideId, Date expireStart,
			Date expireEnd);
}
