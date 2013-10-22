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
 * @version $Id: DiscountExcelHandler.java, v 0.1 2013-10-22 下午4:19:59
 *          narutoying09@gmail.com Exp $
 */
public class DiscountExcelHandler extends ExcelHandler {

	@Override
	public String getHandlerName() {
		return FileHandlerNames.DISCOUNT;
	}

	@Override
	public Object parseRow(HSSFRow hssfRow) {
		return null;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {
	}

}
