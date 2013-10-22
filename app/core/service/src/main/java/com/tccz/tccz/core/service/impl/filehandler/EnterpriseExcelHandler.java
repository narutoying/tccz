/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl.filehandler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.service.manage.BusinessSideManageService;
import com.tccz.tccz.core.service.util.FileHandlerNames;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PersonExcelHandler.java, v 0.1 2013-10-22 上午9:20:12
 *          narutoying09@gmail.com Exp $
 */
public class EnterpriseExcelHandler extends ExcelHandler {

	@Autowired
	private BusinessSideManageService businessSideManageService;

	@Override
	public String getHandlerName() {
		return FileHandlerNames.ENTERPRISE;
	}

	@Override
	public Object parseRow(HSSFRow hssfRow) {
		Enterprise enterprise = new Enterprise();
		enterprise.setName(getValue(hssfRow, 0));
		enterprise.setInstitutionCode(getValue(hssfRow, 1));
		Person legalPerson = new Person();
		legalPerson.setIdCardNumber(getValue(hssfRow, 2));
		enterprise.setLegalPerson(legalPerson);
		return enterprise;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {
		for (Object o : dataModels) {
			Enterprise e = (Enterprise) o;
			businessSideManageService.createEnterprise(e);
		}
	}

}
