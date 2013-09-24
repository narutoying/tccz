/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONSerializer;

import org.springframework.util.CollectionUtils;

import com.tccz.tccz.common.dal.dataobject.DiscountChangeDO;
import com.tccz.tccz.common.dal.dataobject.DiscountDO;
import com.tccz.tccz.common.dal.dataobject.EnterpriseDO;
import com.tccz.tccz.common.dal.dataobject.PersonDO;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.DiscountChange;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;
import com.tccz.tccz.core.service.query.DiscountQueryService;

/**
 * 对象转换器，完成领域对象与DO对象之间的转换
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ObjectConvertor.java, v 0.1 2013-9-13 下午4:57:30
 *          narutoying09@gmail.com Exp $
 */
public class ObjectConvertor {

	public static DiscountDO convertToDiscountDO(Discount domain) {
		if (domain == null) {
			return null;
		}
		DiscountDO result = new DiscountDO();
		result.setId(domain.getId());
		result.setAmount(domain.getAmount().getCent());
		result.setBandarNoteNumber(domain.getBandarNoteNumber());
		result.setCreateTime(domain.getCreateTime());
		result.setExpireDate(domain.getExpireDate());
		result.setModifyTime(domain.getModifyTime());
		result.setProposerId(domain.getProposer().getId());
		result.setState(domain.getState().getCode());
		return result;
	}

	public static Discount convertToDiscount(DiscountDO dataObject,
			DiscountQueryService discountQueryService,
			BusinessSideQueryService businessSideQueryService) {
		if (dataObject == null) {
			return null;
		}
		Discount result = new Discount();
		int id = dataObject.getId();
		result.setAmount(new Money(dataObject.getAmount()));
		result.setBandarNoteNumber(dataObject.getBandarNoteNumber());
		if (discountQueryService != null) {
			List<DiscountChange> queryDiscountChanges = discountQueryService
					.queryDiscountChanges(id);
			result.setChangeHistory(queryDiscountChanges);
			result.setChangeHistoryJson(JSONSerializer
					.toJSON(queryDiscountChanges));
		}
		result.setCreateTime(dataObject.getCreateTime());
		result.setExpireDate(dataObject.getExpireDate());
		result.setId(id);
		result.setModifyTime(dataObject.getModifyTime());
		if (businessSideQueryService != null) {
			result.setProposer(businessSideQueryService
					.queryEnterpriseById(dataObject.getProposerId()));
		}
		result.setState(DiscountState.getByCode(dataObject.getState()));
		return result;
	}

	public static DiscountChange convertToDiscountChange(
			DiscountChangeDO dataObject) {
		if (dataObject == null) {
			return null;
		}
		DiscountChange result = new DiscountChange();
		result.setId(dataObject.getId());
		result.setCreateTime(dataObject.getCreateTime());
		result.setDiscountId(dataObject.getDiscountId());
		result.setState(DiscountState.getByCode(dataObject.getState()));
		return result;
	}

	public static List<DiscountChange> convertToDiscountChangeList(
			List<DiscountChangeDO> list) {
		List<DiscountChange> result = new ArrayList<DiscountChange>();
		if (!CollectionUtils.isEmpty(list)) {
			for (DiscountChangeDO data : list) {
				result.add(convertToDiscountChange(data));
			}
		}
		return result;
	}

	public static Enterprise convertToEnterprise(EnterpriseDO dataObject,
			BusinessSideQueryService businessSideQueryService) {
		if (dataObject == null) {
			return null;
		}
		Enterprise result = new Enterprise();
		result.setId(dataObject.getId());
		result.setName(dataObject.getName());
		result.setAccountNumber(dataObject.getAccountNumber());
		result.setLegalPerson(businessSideQueryService
				.queryPersonById(dataObject.getLegalPersonId()));
		result.setCreateTime(dataObject.getCreateTime());
		result.setModifyTime(dataObject.getModifyTime());
		return result;
	}

	public static List<Enterprise> convertToEnterpriseList(
			List<EnterpriseDO> dataObjects,
			BusinessSideQueryService businessSideQueryService) {
		if (CollectionUtils.isEmpty(dataObjects)) {
			return Collections.emptyList();
		}
		List<Enterprise> result = new ArrayList<Enterprise>();
		for (EnterpriseDO data : dataObjects) {
			result.add(convertToEnterprise(data, businessSideQueryService));
		}
		return result;
	}

	public static Person convertToPerson(PersonDO dataObject) {
		if (dataObject == null) {
			return null;
		}
		Person result = new Person();
		result.setId(dataObject.getId());
		result.setName(dataObject.getName());
		result.setAccountNumber(dataObject.getAccountNumber());
		result.setCreateTime(dataObject.getCreateTime());
		result.setModifyTime(dataObject.getModifyTime());
		result.setOwnEnterprises(null);// TODO 填充企业列表
		return result;
	}
}
