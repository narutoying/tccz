/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideQueryServiceImpl.java, v 0.1 2013-9-17 上午10:08:47
 *          narutoying09@gmail.com Exp $
 */
public class BusinessSideQueryServiceImpl implements BusinessSideQueryService {

	/**
	 * @see com.tccz.tccz.core.service.query.BusinessSideQueryService#queryEnterpriseById(int)
	 */
	@Override
	public Enterprise queryEnterpriseById(int enterpriseId) {
		Enterprise result = new Enterprise();
		result.setName("香塘");
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.query.BusinessSideQueryService#queryPersonById(int)
	 */
	@Override
	public Person queryPersonById(int personId) {
		return null;
	}

}
