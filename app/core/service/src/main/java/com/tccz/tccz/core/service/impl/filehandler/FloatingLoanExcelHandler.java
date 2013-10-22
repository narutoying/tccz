/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl.filehandler;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.tccz.tccz.core.service.util.FileHandlerNames;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanExcelHandler.java, v 0.1 2013-10-22 下午4:18:19
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanExcelHandler extends ExcelHandler {

	@Override
	public String getHandlerName() {
		return FileHandlerNames.FLOATING_LOAN;
	}

	@Override
	public Object parseRow(HSSFRow hssfRow) {
		return null;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {

	}

}
