/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.query;

import java.util.List;

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
	 * 查询企业信息
	 * 
	 * @param enterpriseId
	 * @return
	 */
	Enterprise queryEnterpriseById(int enterpriseId);

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
	List<Enterprise> queryEnterprisesByLegalPerson(int legalPersonId);

	/**
	 * 查询个人信息
	 * 
	 * @param personId
	 * @return
	 */
	Person queryPersonById(int personId);

	/**
	 * 模糊查询个人
	 * 
	 * @param fuzzyName
	 * @return
	 */
	List<Person> fuzzyQueryPersons(String fuzzyName);

	/**
	 * 查询一个业务方所属的集合
	 * <ol>
	 * <li>若businessSide为个人，则查询该人与其关联的企业</li>
	 * <li>若businessSide为企业，则查询该企业、关联的法人及该法人关联的其他企业</li>
	 * </ol>
	 * 
	 * @param businessSide
	 * @return
	 */
	BusinessSideSet queryBusinessSideSet(BusinessSide businessSide);

}
