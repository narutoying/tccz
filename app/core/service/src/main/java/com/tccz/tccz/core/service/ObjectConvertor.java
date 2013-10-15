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

import com.tccz.tccz.common.dal.dataobject.BandarNoteDO;
import com.tccz.tccz.common.dal.dataobject.DiscountChangeDO;
import com.tccz.tccz.common.dal.dataobject.DiscountDO;
import com.tccz.tccz.common.dal.dataobject.EnterpriseDO;
import com.tccz.tccz.common.dal.dataobject.FloatingLoanDO;
import com.tccz.tccz.common.dal.dataobject.PersonDO;
import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.DiscountChange;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.FullMarginBandarNote;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.OpenBandarNote;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.BandarNoteType;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.model.enums.LoanBizSideType;
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

	private static BusinessSideQueryService businessSideQueryService;

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

	public static Enterprise convertToEnterprise(EnterpriseDO dataObject) {
		if (dataObject == null) {
			return null;
		}
		Enterprise result = new Enterprise();
		result.setId(dataObject.getId());
		result.setName(dataObject.getName());
		result.setAccountNumber(dataObject.getAccountNumber());
		// Person person = new Person();
		// person.setId(dataObject.getLegalPersonId());
		// result.setLegalPerson(person);
		result.setLegalPerson(businessSideQueryService.queryPersonById(
				dataObject.getLegalPersonId(), false));
		result.setCreateTime(dataObject.getCreateTime());
		result.setModifyTime(dataObject.getModifyTime());
		return result;
	}

	public static List<Enterprise> convertToEnterpriseList(
			List<EnterpriseDO> dataObjects) {
		if (CollectionUtils.isEmpty(dataObjects)) {
			return Collections.emptyList();
		}
		List<Enterprise> result = new ArrayList<Enterprise>();
		for (EnterpriseDO data : dataObjects) {
			result.add(convertToEnterprise(data));
		}
		return result;
	}

	public static Person convertToPerson(PersonDO dataObject,
			boolean fillEnterprise,
			BusinessSideQueryService businessSideQueryService) {
		if (dataObject == null) {
			return null;
		}
		Person result = new Person();
		result.setId(dataObject.getId());
		result.setName(dataObject.getName());
		result.setAccountNumber(dataObject.getAccountNumber());
		result.setCreateTime(dataObject.getCreateTime());
		result.setModifyTime(dataObject.getModifyTime());
		if (fillEnterprise) {
			result.setOwnEnterprises(businessSideQueryService
					.queryEnterprisesByLegalPerson(dataObject.getId()));
		}
		return result;
	}

	public static BandarNote convertToBandarNote(BandarNoteDO data,
			BusinessSideQueryService businessSideQueryService) {
		if (data == null) {
			return null;
		}
		BandarNoteType type = BandarNoteType.getByCode(data.getType());
		BandarNote result = null;
		if (type == BandarNoteType.OPEN) {
			OpenBandarNote tmpResult = new OpenBandarNote();
			tmpResult.setOpenMoney(new Money(data.getExposureAmount()));
			tmpResult.setCloseMoney(new Money(data.getExposureCloseAmount()));
			result = tmpResult;
		} else if (type == BandarNoteType.FULL_MARGIN) {
			result = new FullMarginBandarNote();
		} else {
			throw new CommonException("不支持的银票类型");
		}
		result.setAmount(new Money(data.getAmount()));
		result.setCreateTime(data.getCreateTime());
		result.setDrawDate(data.getDrawDate());
		result.setDrawer(businessSideQueryService.queryEnterpriseById(data
				.getEnterpriseId()));
		result.setExpireDate(data.getExpireDate());
		result.setId(data.getId());
		result.setMargin(new Money(data.getMarginAmount()));
		result.setModifyTime(data.getModifyTime());
		result.setNumber(data.getBandarNoteNumber());
		result.setType(type);
		return result;
	}

	public static BandarNoteDO convertToBandarNoteDO(BandarNote data) {
		if (data == null) {
			return null;
		}
		BandarNoteDO result = new BandarNoteDO();
		result.setAmount(data.getAmount().getCent());
		result.setBandarNoteNumber(data.getNumber());
		result.setCreateTime(data.getCreateTime());
		result.setDrawDate(data.getDrawDate());
		result.setEnterpriseId(data.getDrawer().getId());
		result.setExpireDate(data.getExpireDate());
		result.setId(data.getId());
		result.setMarginAmount(data.getMargin().getCent());
		if (data instanceof OpenBandarNote) {
			OpenBandarNote obn = (OpenBandarNote) data;
			result.setExposureAmount(obn.getOpenMoney().getCent());
			result.setExposureCloseAmount(obn.getCloseMoney().getCent());
		}
		result.setModifyTime(data.getModifyTime());
		result.setType(data.getType().toString());
		return result;
	}

	public static FloatingLoan convertToFloatingLoan(FloatingLoanDO data,
			BusinessSideQueryService businessSideQueryService) {
		if (data == null) {
			return null;
		}
		FloatingLoan result = new FloatingLoan();
		result.setAmount(new Money(data.getAmount()));
		LoanBizSideType type = LoanBizSideType.getByCode(data.getBizSideType());
		result.setBizSideType(type);
		result.setCreateTime(data.getCreateTime());
		result.setExpireDate(data.getExpireDate());
		result.setId(data.getId());
		if (type == LoanBizSideType.CORPORATE) {
			result.setLoaner(businessSideQueryService.queryEnterpriseById(data
					.getLoanerId()));
		} else if (type == LoanBizSideType.PRIVATE) {
			result.setLoaner(businessSideQueryService.queryPersonById(
					data.getLoanerId(), false));
		}
		result.setModifyTime(data.getModifyTime());
		result.setReleaseDate(data.getReleaseDate());
		return result;
	}

	public static FloatingLoanDO convertToFloatingLoanDO(FloatingLoan data) {
		if (data == null) {
			return null;
		}
		FloatingLoanDO result = new FloatingLoanDO();
		result.setAmount(data.getAmount().getCent());
		result.setBizSideType(data.getBizSideType().toString());
		result.setCreateTime(data.getCreateTime());
		result.setExpireDate(data.getExpireDate());
		result.setId(data.getId());
		result.setLoanerId(data.getLoaner().getId());
		result.setModifyTime(data.getModifyTime());
		result.setReleaseDate(data.getReleaseDate());
		return result;
	}

	public static List<Person> convertToPersonList(
			List<PersonDO> fuzzyQueryByName,
			BusinessSideQueryService businessSideQueryService) {
		List<Person> result = new ArrayList<Person>();
		if (!CollectionUtils.isEmpty(fuzzyQueryByName)) {
			for (PersonDO data : fuzzyQueryByName) {
				result.add(convertToPerson(data, true, businessSideQueryService));
			}
		}
		return result;
	}

	public void setBusinessSideQueryService(
			BusinessSideQueryService businessSideQueryService) {
		ObjectConvertor.businessSideQueryService = businessSideQueryService;
	}
}
