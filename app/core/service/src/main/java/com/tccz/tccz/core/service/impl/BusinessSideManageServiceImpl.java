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
import com.tccz.tccz.common.dal.daointerface.PersonEnterpriseRelationDAO;
import com.tccz.tccz.common.dal.dataobject.PersonEnterpriseRelationDO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.ParaCheckUtil;
import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.common.util.template.CommonManageTemplate;
import com.tccz.tccz.common.util.template.callback.CommonManageCallback;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.PersonEnterpriseRelationType;
import com.tccz.tccz.core.service.ObjectConvertor;
import com.tccz.tccz.core.service.manage.BusinessSideManageService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideManageServiceImpl.java, v 0.1 2013-10-17 下午4:25:04
 *          narutoying09@gmail.com Exp $
 */
public class BusinessSideManageServiceImpl implements BusinessSideManageService {

	@Autowired
	private EnterpriseDAO enterpriseDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private PersonEnterpriseRelationDAO personEnterpriseRelationDAO;

	@Autowired
	private CommonManageTemplate commonManageTemplate;

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#createEnterprise(com.tccz.tccz.core.model.Enterprise)
	 */
	@Override
	public CommonResult createEnterprise(final Enterprise enterprise) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						enterpriseDAO.insert(ObjectConvertor
								.convertToEnterpriseDO(enterprise));
						PersonEnterpriseRelationDO personEnterpriseRelation = new PersonEnterpriseRelationDO();
						personEnterpriseRelation.setEnterpriseId(enterprise
								.getIdentifier());
						personEnterpriseRelation.setPersonId(enterprise
								.getLegalPerson().getIdentifier());
						personEnterpriseRelation
								.setRelationType(PersonEnterpriseRelationType.LEGAL
										.name());
						personEnterpriseRelationDAO
								.insert(personEnterpriseRelation);
						CommonResult.buildResult(result, true, "创建企业成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(enterprise);
						ParaCheckUtil.checkParaNotBlank(enterprise
								.getIdentifier());
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#deleteEnterprise(java.lang.String)
	 */
	@Override
	public CommonResult deleteEnterprise(final String id) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						enterpriseDAO.delete(id);
						personEnterpriseRelationDAO.deleteByEnterpriseId(id);
						// 删除时是否顺带删除所有关联的业务数据？
						CommonResult.buildResult(result, true, "删除企业成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotBlank(id);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#updateEnterprse(com.tccz.tccz.core.model.Enterprise)
	 */
	@Override
	public CommonResult updateEnterprse(final Enterprise enterprise) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						enterpriseDAO.update(ObjectConvertor
								.convertToEnterpriseDO(enterprise));
						/*
						 * 1. 法人信息变更需修改对应relation 2. 此处更新不涉及非法人关联
						 */
						String identifier = enterprise.getLegalPerson()
								.getIdentifier();
						personEnterpriseRelationDAO.updateByEnterpriseAndType(
								identifier, enterprise.getIdentifier(),
								PersonEnterpriseRelationType.LEGAL.name());
						CommonResult.buildResult(result, true, "更新企业成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(enterprise);
						ParaCheckUtil.checkParaNotBlank(enterprise
								.getIdentifier());
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#associatePersonToEnterprise(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public CommonResult associatePersonToEnterprise(final String enterpriseId,
			final String personId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						PersonEnterpriseRelationDO personEnterpriseRelation = new PersonEnterpriseRelationDO();
						personEnterpriseRelation.setEnterpriseId(enterpriseId);
						personEnterpriseRelation.setPersonId(personId);
						personEnterpriseRelation
								.setRelationType(PersonEnterpriseRelationType.NORMAL
										.name());
						personEnterpriseRelationDAO
								.insert(personEnterpriseRelation);
						CommonResult.buildResult(result, true, "关联个人到企业成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotBlank(enterpriseId);
						ParaCheckUtil.checkParaNotBlank(personId);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#deleteAssociatePersonToEnterprise(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public CommonResult deleteAssociatePersonToEnterprise(
			final String enterpriseId, final String personId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						personEnterpriseRelationDAO
								.deleteByEnterpriseAndPersonId(personId,
										enterpriseId);
						CommonResult.buildResult(result, true, "取消关联个人到企业成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotBlank(enterpriseId);
						ParaCheckUtil.checkParaNotBlank(personId);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#createPerson(com.tccz.tccz.core.model.Person)
	 */
	@Override
	public CommonResult createPerson(final Person person) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						personDAO.insert(ObjectConvertor
								.convertToPersonDO(person));
						CommonResult.buildResult(result, true, "创建个人成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(person);
						ParaCheckUtil.checkParaNotBlank(person.getIdentifier());
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#deletePerson(java.lang.String)
	 */
	@Override
	public CommonResult deletePerson(final String id) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						List<PersonEnterpriseRelationDO> list = personEnterpriseRelationDAO
								.getByCondition(null, id,
										PersonEnterpriseRelationType.LEGAL
												.name());
						if (!CollectionUtils.isEmpty(list)) {
							throw new CommonException("个人[" + id
									+ "]是企业法人，不允许删除");
						} else {
							personDAO.delete(id);
						}
						CommonResult.buildResult(result, true, "删除个人成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotBlank(id);
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#updatePerson(com.tccz.tccz.core.model.Person)
	 */
	@Override
	public CommonResult updatePerson(final Person person) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						personDAO.update(ObjectConvertor
								.convertToPersonDO(person));
						CommonResult.buildResult(result, true, "更新成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(person);
						ParaCheckUtil.checkParaNotBlank(person.getIdentifier());
					}
				});
		return result;
	}

}
