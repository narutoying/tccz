/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.common.dal.daointerface.EnterpriseDAO;
import com.tccz.tccz.common.dal.daointerface.PersonDAO;
import com.tccz.tccz.common.dal.daointerface.PersonEnterpriseRelationDAO;
import com.tccz.tccz.common.dal.dataobject.PersonEnterpriseRelationDO;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.BusinessSideSet;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.BusinessSideSetType;
import com.tccz.tccz.core.model.enums.PersonEnterpriseRelationType;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideQueryServiceImpl.java, v 0.1 2013-9-17 上午10:08:47
 *          narutoying09@gmail.com Exp $
 */
public class BusinessSideQueryServiceImpl implements BusinessSideQueryService {

	/** 辅助计算业务方结合Map */
	private ThreadLocal<Map<BusinessSide, Boolean>> businessSideSetHelperMap = new ThreadLocal<Map<BusinessSide, Boolean>>() {
		@Override
		protected Map<BusinessSide, Boolean> initialValue() {
			return new HashMap<BusinessSide, Boolean>();
		}
	};

	@Autowired
	private EnterpriseDAO enterpriseDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private PersonEnterpriseRelationDAO personEnterpriseRelationDAO;

	@Override
	public List<Enterprise> fuzzyQueryEnterprises(String fuzzyEnterpriseName) {
		return ObjectConvertor.convertToEnterpriseList(enterpriseDAO
				.fuzzyQueryByName(fuzzyEnterpriseName));
	}

	@Override
	public List<Person> fuzzyQueryPersons(String fuzzyName) {
		return ObjectConvertor.convertToPersonList(
				personDAO.fuzzyQueryByName(fuzzyName), false);
	}

	@Override
	public BusinessSideSet queryBusinessSideSet(BusinessSide businessSide) {
		BusinessSideSet recursiveQueryBusinessSideSet = recursiveQueryBusinessSideSet(businessSide);
		BusinessSideSetType type = null;
		if (recursiveQueryBusinessSideSet != null) {
			List<Enterprise> enterprises = recursiveQueryBusinessSideSet
					.getEnterprises();
			if (!CollectionUtils.isEmpty(enterprises) && enterprises.size() > 1) {
				type = BusinessSideSetType.UNITED;
			} else {
				type = BusinessSideSetType.SINGLE;
			}
			recursiveQueryBusinessSideSet.setType(type);
		}
		businessSideSetHelperMap.remove();
		return recursiveQueryBusinessSideSet;
	}

	private BusinessSideSet recursiveQueryBusinessSideSet(
			BusinessSide businessSide) {
		/*
		 * 算法描述：
		 * 
		 * a) 若businessSide为个人，则将其本身、直接关联的法人企业与非法人企业加入结果集， 然后继续递归地将这些企业的集合加入结果集；
		 * 
		 * b) 若businessSide为企业，则将其本身、直接关联的法人与非法人加入结果集， 然后继续递归地将这些个人的集合加入结果集；
		 * 
		 * 注：businessSideSetHelperMap用于标记计算过程中某业务方是否已经被包含在结果集，以免重复计算甚至无限递归
		 */
		if (businessSide == null) {
			return null;
		}
		if (isQueryed(businessSide)) {
			return null;
		}
		BusinessSideSet result = new BusinessSideSet();
		String bizSideId = businessSide.getIdentifier();
		if (businessSide instanceof Person) {
			Person person = queryPersonByIdCard(bizSideId, true);
			result.getPersons().add(person);
			setQueryed(person);
			List<Enterprise> ownEnterprises = person.getOwnEnterprises();
			for (Enterprise enterprise : ownEnterprises) {
				result.combine(recursiveQueryBusinessSideSet(enterprise));
			}
			List<Enterprise> relationEnterprises = person
					.getRelationEnterprises();
			for (Enterprise enterprise : relationEnterprises) {
				result.combine(recursiveQueryBusinessSideSet(enterprise));
			}
		} else if (businessSide instanceof Enterprise) {
			Enterprise enterprise = queryEnterpriseByInstitudeCode(bizSideId);
			result.getEnterprises().add(enterprise);
			setQueryed(enterprise);
			Person legalPerson = enterprise.getLegalPerson();
			result.combine(recursiveQueryBusinessSideSet(legalPerson));
			List<Person> relationPersons = enterprise.getRelationPersons();
			if (!CollectionUtils.isEmpty(relationPersons)) {
				for (Person relationPerson : relationPersons) {
					result.combine(recursiveQueryBusinessSideSet(relationPerson));
				}
			}
		}
		return result;
	}

	private void setQueryed(BusinessSide businessSide) {
		Map<BusinessSide, Boolean> map = businessSideSetHelperMap.get();
		map.put(businessSide, true);
	}

	private boolean isQueryed(BusinessSide businessSide) {
		Map<BusinessSide, Boolean> map = businessSideSetHelperMap.get();
		Boolean exist = map.get(businessSide);
		if (exist != null && exist == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Enterprise> queryEnterprisesByLegalPerson(
			String legalPersonIdCard) {
		return ObjectConvertor.convertToEnterpriseList(enterpriseDAO
				.getByLegalPersonIdCard(legalPersonIdCard));
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
		result.setDataList(ObjectConvertor.convertToPersonList(pageList, true));
		result.setPaginator(pageList.getPaginator());
		return result;
	}

	@Override
	public Enterprise queryEnterpriseByInstitudeCode(String institudeCode) {
		return ObjectConvertor.convertToEnterprise(enterpriseDAO
				.getEnterpriseByCode(institudeCode));
	}

	@Override
	public Person queryPersonByIdCard(String personIdCard,
			boolean fillEnterprise) {
		return ObjectConvertor.convertToPerson(
				personDAO.getByIdCard(personIdCard), fillEnterprise);
	}

	@Override
	public Person queryLegalPerson(String institutionCode,
			boolean fillEnterprise) {
		List<PersonEnterpriseRelationDO> list = personEnterpriseRelationDAO
				.getByCondition(institutionCode, null,
						PersonEnterpriseRelationType.LEGAL.name());
		if (!CollectionUtils.isEmpty(list) && list.size() == 1) {
			return queryPersonByIdCard(list.get(0).getPersonId(),
					fillEnterprise);
		}
		return null;
	}
}
