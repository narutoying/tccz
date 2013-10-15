/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.common.dal.daointerface.EnterpriseDAO;
import com.tccz.tccz.common.dal.daointerface.PersonDAO;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.BusinessSideSet;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.BusinessSideSetType;
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
		return ObjectConvertor.convertToEnterprise(enterpriseDAO
				.getById(enterpriseId));
	}

	/**
	 * @see com.tccz.tccz.core.service.query.BusinessSideQueryService#queryPersonById(int)
	 */
	@Override
	public Person queryPersonById(int personId, boolean fillEnterprise) {
		return ObjectConvertor.convertToPerson(personDAO.getById(personId),
				fillEnterprise, this);
	}

	@Override
	public List<Enterprise> fuzzyQueryEnterprises(String fuzzyEnterpriseName) {
		return ObjectConvertor.convertToEnterpriseList(enterpriseDAO
				.fuzzyQueryByName(fuzzyEnterpriseName));
	}

	@Override
	public List<Person> fuzzyQueryPersons(String fuzzyName) {
		return ObjectConvertor.convertToPersonList(
				personDAO.fuzzyQueryByName(fuzzyName), this);
	}

	@Override
	public BusinessSideSet queryBusinessSideSet(BusinessSide businessSide) {
		BusinessSideSet result = new BusinessSideSet();
		int bizSideId = businessSide.getId();
		BusinessSideSetType type = null;
		Person person = null;
		List<Enterprise> enterprises = null;
		if (businessSide instanceof Person) {
			person = queryPersonById(bizSideId, true);
			enterprises = person.getOwnEnterprises();
		} else if (businessSide instanceof Enterprise) {
			Enterprise enterprise = queryEnterpriseById(bizSideId);
			person = queryPersonById(enterprise.getLegalPerson().getId(), true);
			enterprises = person.getOwnEnterprises();
		}
		if (!CollectionUtils.isEmpty(enterprises) && enterprises.size() > 1) {
			type = BusinessSideSetType.UNITED;
		} else {
			type = BusinessSideSetType.SINGLE;
		}
		result.setType(type);
		result.setPerson(person);
		result.setEnterprises(enterprises);
		return result;
	}

	@Override
	public List<Enterprise> queryEnterprisesByLegalPerson(int legalPersonId) {
		return ObjectConvertor.convertToEnterpriseList(enterpriseDAO
				.getByLegalPersonId(legalPersonId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageList<Enterprise> fuzzyQueryEnterprisesPage(
			String enterpriseName, int page, int pageSize) {
		PageList<Enterprise> result = new PageList<Enterprise>();
		com.tccz.tccz.dal.util.PageList pageList = enterpriseDAO
				.fuzzyPageQueryByName(enterpriseName, pageSize,
						PageUtil.getOffset(pageSize, page));
		result.setDataList(ObjectConvertor.convertToEnterpriseList(pageList));
		result.setPaginator(pageList.getPaginator());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageList<Person> fuzzyQueryPersonsPage(String personName, int page,
			int pageSize) {
		PageList<Person> result = new PageList<Person>();
		com.tccz.tccz.dal.util.PageList pageList = personDAO
				.fuzzyPageQueryByName(personName, pageSize,
						PageUtil.getOffset(pageSize, page));
		result.setDataList(ObjectConvertor.convertToPersonList(pageList, this));
		result.setPaginator(pageList.getPaginator());
		return result;
	}
}
