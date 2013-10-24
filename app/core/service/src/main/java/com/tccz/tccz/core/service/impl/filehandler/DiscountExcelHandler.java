/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl.filehandler;

import java.text.ParseException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.util.DateUtil;
import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.service.manage.DiscountManageService;
import com.tccz.tccz.core.service.util.FileHandlerNames;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountExcelHandler.java, v 0.1 2013-10-22 下午4:19:59
 *          narutoying09@gmail.com Exp $
 */
public class DiscountExcelHandler extends ExcelHandler {

	@Autowired
	private DiscountManageService discountManageService;

	@Override
	public String getHandlerName() {
		return FileHandlerNames.DISCOUNT;
	}

	/**
	 * excel表格字段：
	 * <ol>
	 * <li>0 银票号</li>
	 * <li>1 企业机构号</li>
	 * <li>2 企业名</li>
	 * <li>3 银票金额</li>
	 * <li>4 状态</li>
	 * <li>5 到期日</li>
	 * </ol>
	 * 
	 * @see com.tccz.tccz.core.service.impl.filehandler.ExcelHandler#parseRow(org.apache.poi.hssf.usermodel.HSSFRow)
	 */
	@Override
	public Object parseRow(HSSFRow hssfRow) {
		Discount result = new Discount();
		result.setAmount(new Money(getValue(hssfRow, 3)));
		result.setBandarNoteNumber(getValue(hssfRow, 0));
		String dateStr = getValue(hssfRow, 5);
		try {
			result.setExpireDate(DateUtil
					.parseDateNoTime(dateStr, "yyyy-MM-dd"));
		} catch (ParseException e) {
			throw new CommonException("解析日期[" + dateStr + "]出错", e);
		}
		Enterprise proposer = new Enterprise();
		proposer.setIdentifier(getValue(hssfRow, 1));
		result.setProposer(proposer);
		String stateStr = getValue(hssfRow, 4);
		DiscountState state = DiscountState.getByDesc(stateStr);
		if (state == null) {
			throw new CommonException("不存在的银票状态【" + stateStr + "】");
		}
		result.setState(state);
		return result;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {
		for (Object o : dataModels) {
			Discount data = (Discount) o;
			discountManageService.createDiscount(data);
		}
	}

}
