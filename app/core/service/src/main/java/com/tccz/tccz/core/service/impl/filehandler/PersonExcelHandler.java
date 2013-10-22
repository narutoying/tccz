/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl.filehandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.service.manage.BusinessSideManageService;
import com.tccz.tccz.core.service.util.FileHandlerNames;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PersonExcelHandler.java, v 0.1 2013-10-22 上午9:20:12
 *          narutoying09@gmail.com Exp $
 */
public class PersonExcelHandler extends ExcelHandler {

	@Autowired
	private BusinessSideManageService businessSideManageService;

	@Override
	public String getHandlerName() {
		return FileHandlerNames.PERSON;
	}

	@Override
	public Object parseRow(HSSFRow hssfRow) {
		Person person = new Person();
		String name = getValue(hssfRow, 0);
		String idCard = getValue(hssfRow, 1);
		person.setIdCardNumber(idCard);
		person.setName(name);
		return person;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {
		businessSideManageService
				.batchCreatePersons(convertToModels(dataModels));
	}

	private List<Person> convertToModels(List<Object> dataModels) {
		List<Person> result = new ArrayList<Person>();
		for (Object o : dataModels) {
			result.add((Person) o);
		}
		return result;
	}

}
