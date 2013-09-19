/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;

/**
 * 业务方查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideQueryService.java, v 0.1 2013-9-17 上午10:04:16
 *          narutoying09@gmail.com Exp $
 */
public interface BusinessSideQueryService {

	/**
	 * 查询企业信息
	 * 
	 * @param enterpriseId
	 * @return
	 */
	Enterprise queryEnterpriseById(int enterpriseId);

	/**
	 * 查询个人信息
	 * 
	 * @param personId
	 * @return
	 */
	Person queryPersonById(int personId);
}
