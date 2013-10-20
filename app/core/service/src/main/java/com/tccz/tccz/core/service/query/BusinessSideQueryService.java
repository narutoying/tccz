/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

import java.util.List;

import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.BusinessSideSet;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;

/**
 * 业务方查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideQueryService.java, v 0.1 2013-9-17 上午10:04:16
 *          narutoying09@gmail.com Exp $
 */
public interface BusinessSideQueryService {

	/**
	 * 按照机构号查询企业
	 * 
	 * @param institudeCode
	 * @return
	 */
	Enterprise queryEnterpriseByInstitudeCode(String institudeCode);

	/**
	 * 模糊查询企业
	 * 
	 * @param fuzzyEnterpriseName
	 *            企业名
	 * @return
	 */
	List<Enterprise> fuzzyQueryEnterprises(String fuzzyEnterpriseName);

	/**
	 * 查询指定法人所关联的企业列表
	 * 
	 * @param legalPersonId
	 * @return
	 */
	List<Enterprise> queryEnterprisesByLegalPerson(String legalPersonIdCard);

	/**
	 * 模糊、分页查询企业列表
	 * 
	 * @param enterpriseName
	 * @return
	 */
	PageList<Enterprise> fuzzyQueryEnterprisesPage(String enterpriseName,
			int page, int pageSize);

	/**
	 * 通过身份证号查询个人信息
	 * 
	 * @param personIdCard
	 * @param fillEnterprise
	 * @return
	 */
	Person queryPersonByIdCard(String personIdCard, boolean fillEnterprise);

	/**
	 * 查询企业法人信息
	 * 
	 * @param institutionCode
	 * @param fillEnterprise
	 * @return
	 */
	Person queryLegalPerson(String institutionCode, boolean fillEnterprise);

	/**
	 * 模糊查询个人
	 * 
	 * @param fuzzyName
	 * @return
	 */
	List<Person> fuzzyQueryPersons(String fuzzyName);

	/**
	 * 模糊、分页查询个人列表
	 * 
	 * @param enterpriseName
	 * @return
	 */
	PageList<Person> fuzzyQueryPersonsPage(String personName, int page,
			int pageSize);

	/**
	 * 查询一个业务方所属的集合
	 * <ol>
	 * <li>若businessSide为个人，则集合范围包括：该个人、直接关联企业、直接法人企业以及间接关联人/企业</li>
	 * <li>若businessSide为企业，则查询该企业、直接关联法人及该法人直接/间接关联的其他企业、个人</li>
	 * </ol>
	 * 
	 * @param businessSide
	 * @return
	 */
	BusinessSideSet queryBusinessSideSet(BusinessSide businessSide);

}
