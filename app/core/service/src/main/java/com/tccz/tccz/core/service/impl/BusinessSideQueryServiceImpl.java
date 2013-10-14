/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.EnterpriseDAO;
import com.tccz.tccz.common.dal.daointerface.PersonDAO;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideQueryServiceImpl.java, v 0.1 2013-9-17 上午10:08:47
 *          narutoying09@gmail.com Exp $
 */
public class BusinessSideQueryServiceImpl implements BusinessSideQueryService {

	@Autowired
	private EnterpriseDAO enterpriseDAO;

	@Autowired
	private PersonDAO personDAO;

	/**
	 * @see com.tccz.tccz.core.service.query.BusinessSideQueryService#queryEnterpriseById(int)
	 */
	@Override
	public Enterprise queryEnterpriseById(int enterpriseId) {
		return ObjectConvertor.convertToEnterprise(
				enterpriseDAO.getById(enterpriseId), this);
	}

	/**
	 * @see com.tccz.tccz.core.service.query.BusinessSideQueryService#queryPersonById(int)
	 */
	@Override
	public Person queryPersonById(int personId) {
		return ObjectConvertor.convertToPerson(personDAO.getById(personId));
	}

	@Override
	public List<Enterprise> fuzzyQueryEnterprises(String fuzzyEnterpriseName) {
		return ObjectConvertor.convertToEnterpriseList(
				enterpriseDAO.fuzzyQueryByName(fuzzyEnterpriseName), this);
	}

	@Override
	public List<Person> fuzzyQueryPersons(String fuzzyName) {
		return ObjectConvertor.convertToPersonList(personDAO
				.fuzzyQueryByName(fuzzyName));
	}

}
