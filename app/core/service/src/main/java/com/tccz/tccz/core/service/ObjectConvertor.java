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

import com.tccz.tccz.common.dal.daointerface.PersonEnterpriseRelationDAO;
import com.tccz.tccz.common.dal.dataobject.BandarNoteDO;
import com.tccz.tccz.common.dal.dataobject.DiscountChangeDO;
import com.tccz.tccz.common.dal.dataobject.DiscountDO;
import com.tccz.tccz.common.dal.dataobject.EnterpriseDO;
import com.tccz.tccz.common.dal.dataobject.FloatingLoanDO;
import com.tccz.tccz.common.dal.dataobject.PersonDO;
import com.tccz.tccz.common.dal.dataobject.PersonEnterpriseRelationDO;
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
import com.tccz.tccz.core.model.enums.PersonEnterpriseRelationType;
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

	private static PersonEnterpriseRelationDAO personEnterpriseRelationDAO;

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
		result.setInstitutionCode(domain.getProposer().getInstitutionCode());
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
					.queryEnterpriseByInstitudeCode(dataObject
							.getInstitutionCode()));
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
		String institutionCode = dataObject.getInstitutionCode();
		result.setInstitutionCode(institutionCode);
		result.setCreateTime(dataObject.getCreateTime());
		result.setModifyTime(dataObject.getModifyTime());
		List<Person> relationPersons = new ArrayList<Person>();
		result.setRelationPersons(relationPersons);
		List<PersonEnterpriseRelationDO> relationDOs = personEnterpriseRelationDAO
				.getByCondition(institutionCode, null, null);
		if (!CollectionUtils.isEmpty(relationDOs)) {
			for (PersonEnterpriseRelationDO relation : relationDOs) {
				PersonEnterpriseRelationType type = PersonEnterpriseRelationType
						.getByCode(relation.getRelationType());
				Person person = businessSideQueryService.queryPersonByIdCard(
						relation.getPersonId(), false);
				if (type == PersonEnterpriseRelationType.LEGAL) {
					result.setLegalPerson(person);
				} else if (type == PersonEnterpriseRelationType.NORMAL) {
					relationPersons.add(person);
				}
			}
		}
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
			boolean fillEnterprise) {
		if (dataObject == null) {
			return null;
		}
		Person result = new Person();
		result.setId(dataObject.getId());
		result.setName(dataObject.getName());
		result.setAccountNumber(dataObject.getAccountNumber());
		result.setCreateTime(dataObject.getCreateTime());
		result.setModifyTime(dataObject.getModifyTime());
		result.setIdCardNumber(dataObject.getIdCardNumber());
		List<PersonEnterpriseRelationDO> relations = personEnterpriseRelationDAO
				.getByCondition(null, dataObject.getIdCardNumber(), null);
		if (!CollectionUtils.isEmpty(relations) && fillEnterprise) {
			List<Enterprise> ownEnterprises = new ArrayList<Enterprise>();
			List<Enterprise> relationEnterprises = new ArrayList<Enterprise>();
			result.setOwnEnterprises(ownEnterprises);
			result.setRelationEnterprises(relationEnterprises);
			for (PersonEnterpriseRelationDO relationDO : relations) {
				PersonEnterpriseRelationType type = PersonEnterpriseRelationType
						.getByCode(relationDO.getRelationType());
				if (type == PersonEnterpriseRelationType.LEGAL) {
					ownEnterprises.add(businessSideQueryService
							.queryEnterpriseByInstitudeCode(relationDO
									.getEnterpriseId()));
				} else if (type == PersonEnterpriseRelationType.NORMAL) {
					relationEnterprises.add(businessSideQueryService
							.queryEnterpriseByInstitudeCode(relationDO
									.getEnterpriseId()));
				}
			}
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
		result.setDrawer(businessSideQueryService
				.queryEnterpriseByInstitudeCode(data.getInstitutionCode()));
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
		result.setInstitutionCode(data.getDrawer().getInstitutionCode());
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
			result.setLoaner(businessSideQueryService
					.queryEnterpriseByInstitudeCode(data.getLoanerId()));
		} else if (type == LoanBizSideType.PRIVATE) {
			result.setLoaner(businessSideQueryService.queryPersonByIdCard(
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
		result.setLoanerId(data.getLoaner().getIdentifier());
		result.setModifyTime(data.getModifyTime());
		result.setReleaseDate(data.getReleaseDate());
		return result;
	}

	public static List<Person> convertToPersonList(
			List<PersonDO> fuzzyQueryByName, boolean fillEnterprise) {
		List<Person> result = new ArrayList<Person>();
		if (!CollectionUtils.isEmpty(fuzzyQueryByName)) {
			for (PersonDO data : fuzzyQueryByName) {
				result.add(convertToPerson(data, fillEnterprise));
			}
		}
		return result;
	}

	public void setBusinessSideQueryService(
			BusinessSideQueryService businessSideQueryService) {
		ObjectConvertor.businessSideQueryService = businessSideQueryService;
	}

	public void setPersonEnterpriseRelationDAO(
			PersonEnterpriseRelationDAO personEnterpriseRelationDAO) {
		ObjectConvertor.personEnterpriseRelationDAO = personEnterpriseRelationDAO;
	}

	public static EnterpriseDO convertToEnterpriseDO(Enterprise enterprise) {
		if (enterprise == null) {
			return null;
		}
		EnterpriseDO result = new EnterpriseDO();
		result.setAccountNumber(enterprise.getAccountNumber());
		result.setCreateTime(enterprise.getCreateTime());
		result.setId(enterprise.getId());
		result.setInstitutionCode(enterprise.getInstitutionCode());
		result.setModifyTime(enterprise.getModifyTime());
		result.setName(enterprise.getName());
		return result;
	}

	public static PersonDO convertToPersonDO(Person person) {
		if (person == null) {
			return null;
		}
		PersonDO result = new PersonDO();
		result.setAccountNumber(person.getAccountNumber());
		result.setCreateTime(person.getCreateTime());
		result.setId(person.getId());
		result.setIdCardNumber(person.getIdentifier());
		result.setModifyTime(person.getModifyTime());
		result.setName(person.getName());
		return result;
	}

}
