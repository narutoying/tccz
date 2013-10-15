/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.biz.service;

import com.tccz.tccz.biz.model.EnterpriseLimitView;
import com.tccz.tccz.biz.model.PersonLimitView;
import com.tccz.tccz.common.util.PageList;

/**
 * 额度业务服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitBizService.java, v 0.1 2013-10-15 上午8:41:48
 *          narutoying09@gmail.com Exp $
 */
public interface LimitBizService {

	/**
	 * 查询企业额度总览
	 * 
	 * @param fuzzyEnterpriseName
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageList<EnterpriseLimitView> queryEnterpriseLimitViews(
			String fuzzyEnterpriseName, int page, int pageSize);

	/**
	 * 查询个人额度总览
	 * 
	 * @param fuzzyPersonName
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageList<PersonLimitView> queryPersonLimitViews(String fuzzyPersonName,
			int page, int pageSize);
}
