/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.biz.model.EnterpriseLimitView;
import com.tccz.tccz.biz.model.PersonLimitView;
import com.tccz.tccz.biz.service.LimitBizService;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.BusinessSideSet;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitBizServiceImpl.java, v 0.1 2013-10-15 上午10:00:19
 *          narutoying09@gmail.com Exp $
 */
public class LimitBizServiceImpl implements LimitBizService {

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@Autowired
	private LimitService limitService;

	/**
	 * @see com.tccz.tccz.biz.service.LimitBizService#queryEnterpriseLimitViews(java.lang.String,
	 *      int, int)
	 */
	@Override
	public PageList<EnterpriseLimitView> queryEnterpriseLimitViews(
			String fuzzyEnterpriseName, int page, int pageSize) {
		PageList<EnterpriseLimitView> result = new PageList<EnterpriseLimitView>();
		PageList<Enterprise> fuzzyQueryEnterprisesPage = businessSideQueryService
				.fuzzyQueryEnterprisesPage(fuzzyEnterpriseName, page, pageSize);
		if (fuzzyQueryEnterprisesPage != null) {
			result.setPaginator(fuzzyQueryEnterprisesPage.getPaginator());
			result.setDataList(buildEnterpriseDataList(fuzzyQueryEnterprisesPage
					.getDataList()));
		}
		return result;
	}

	private List<EnterpriseLimitView> buildEnterpriseDataList(
			List<Enterprise> dataList) {
		List<EnterpriseLimitView> result = new ArrayList<EnterpriseLimitView>();
		if (!CollectionUtils.isEmpty(dataList)) {
			for (Enterprise data : dataList) {
				EnterpriseLimitView view = new EnterpriseLimitView();
				BusinessSideSet businessSideSet = businessSideQueryService
						.queryBusinessSideSet(data);
				data.setLegalPerson(businessSideQueryService.queryLegalPerson(
						data.getInstitutionCode(), false));
				view.setEnterprise(data);
				List<Enterprise> enterprises = businessSideSet.getEnterprises();
				enterprises.remove(data);
				view.setAssociateEnterprises(enterprises);
				List<Person> persons = businessSideSet.getPersons();
				persons.remove(data.getLegalPerson());
				view.setAssociatePersons(persons);
				view.setBusinessSideSetType(businessSideSet.getType());
				view.setTotalLimit(limitService.calculateTotalLimit(data));
				view.setAvailableLimit(limitService
						.calculateAvailableLimit(data));
				result.add(view);
			}
		}
		return result;
	}

	/**
	 * @see com.tccz.tccz.biz.service.LimitBizService#queryPersonLimitViews(java.lang.String,
	 *      int, int)
	 */
	@Override
	public PageList<PersonLimitView> queryPersonLimitViews(
			String fuzzyPersonName, int page, int pageSize) {
		PageList<PersonLimitView> result = new PageList<PersonLimitView>();
		PageList<Person> fuzzyQueryPersonsPage = businessSideQueryService
				.fuzzyQueryPersonsPage(fuzzyPersonName, page, pageSize);
		if (fuzzyQueryPersonsPage != null) {
			result.setPaginator(fuzzyQueryPersonsPage.getPaginator());
			result.setDataList(buildPersonDataList(fuzzyQueryPersonsPage
					.getDataList()));
		}
		return result;
	}

	private List<PersonLimitView> buildPersonDataList(List<Person> dataList) {
		List<PersonLimitView> result = new ArrayList<PersonLimitView>();
		if (!CollectionUtils.isEmpty(dataList)) {
			for (Person person : dataList) {
				PersonLimitView item = new PersonLimitView();
				BusinessSideSet businessSideSet = businessSideQueryService
						.queryBusinessSideSet(person);
				List<Enterprise> ownEnterprises = businessSideQueryService
						.queryEnterprisesByLegalPerson(person.getIdCardNumber());
				item.setOwnEnterprises(ownEnterprises);
				List<Enterprise> enterprises = businessSideSet.getEnterprises();
				enterprises.removeAll(ownEnterprises);
				item.setAssociateEnterprises(enterprises);
				List<Person> persons = businessSideSet.getPersons();
				persons.remove(person);
				item.setAssociatePersons(persons);
				item.setBusinessSideSetType(businessSideSet.getType());
				item.setPerson(person);
				item.setAvailableLimit(limitService
						.calculateAvailableLimit(person));
				item.setTotalLimit(limitService.calculateTotalLimit(person));
				result.add(item);
			}
		}
		return result;
	}

}
