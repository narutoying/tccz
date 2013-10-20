/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.EnterpriseDAO;
import com.tccz.tccz.common.dal.daointerface.PersonDAO;
import com.tccz.tccz.common.dal.daointerface.PersonEnterpriseRelationDAO;
import com.tccz.tccz.common.dal.dataobject.PersonEnterpriseRelationDO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.ParaCheckUtil;
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
						CommonResult.buildResult(result, true, "删除企业成功");
					}

					@Override
					public void checkParameter() {
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
						// 法人信息变更需修改对应relation TODO
						
						CommonResult.buildResult(result, true, "成功");
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#associatePersonToEnterprise(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public CommonResult associatePersonToEnterprise(String enterpriseId,
			String personId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						CommonResult.buildResult(result, true, "成功");
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#deleteAssociatePersonToEnterprise(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public CommonResult deleteAssociatePersonToEnterprise(String enterpriseId,
			String personId) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						CommonResult.buildResult(result, true, "成功");
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#createPerson(com.tccz.tccz.core.model.Person)
	 */
	@Override
	public CommonResult createPerson(Person person) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						CommonResult.buildResult(result, true, "成功");
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#deletePerson(java.lang.String)
	 */
	@Override
	public CommonResult deletePerson(String id) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						CommonResult.buildResult(result, true, "成功");
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

	/**
	 * @see com.tccz.tccz.core.service.manage.BusinessSideManageService#updatePerson(com.tccz.tccz.core.model.Person)
	 */
	@Override
	public CommonResult updatePerson(Person person) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						CommonResult.buildResult(result, true, "成功");
					}

					@Override
					public void checkParameter() {
					}
				});
		return result;
	}

}
