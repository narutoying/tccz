/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.manage;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;

/**
 * 业务方操作服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideManageService.java, v 0.1 2013-10-17 下午4:01:22
 *          narutoying09@gmail.com Exp $
 */
public interface BusinessSideManageService {

	CommonResult createEnterprise(Enterprise enterprise);

	CommonResult deleteEnterprise(String id);

	CommonResult updateEnterprse(Enterprise enterprise);

	/**
	 * 关联人到指定企业，目前只作为该企业非法人
	 * 
	 * @param enterpriseId
	 * @param personId
	 * @return
	 */
	CommonResult associatePersonToEnterprise(String enterpriseId,
			String personId);

	/**
	 * 取消关联人到指定企业
	 * 
	 * @param enterpriseId
	 * @param personId
	 * @return
	 */
	CommonResult deleteAssociatePersonToEnterprise(String enterpriseId,
			String personId);

	CommonResult createPerson(Person person);

	CommonResult deletePerson(String id);

	CommonResult updatePerson(Person person);

}
