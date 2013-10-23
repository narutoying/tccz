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
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.LoanBizSideType;
import com.tccz.tccz.core.service.manage.FloatingLoanManageService;
import com.tccz.tccz.core.service.util.FileHandlerNames;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanExcelHandler.java, v 0.1 2013-10-22 下午4:18:19
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanExcelHandler extends ExcelHandler {

	@Autowired
	private FloatingLoanManageService floatingLoanManageService;

	@Override
	public String getHandlerName() {
		return FileHandlerNames.FLOATING_LOAN;
	}

	/**
	 * excel表格说明
	 * <ol>
	 * <li>借款人（机构号/身份证号）</li>
	 * <li>借款人名</li>
	 * <li>类型（对公/对私）</li>
	 * <li>贷款金（余）额</li>
	 * <li>发放日期</li>
	 * <li>到期日</li>
	 * </ol>
	 * 
	 * @see com.tccz.tccz.core.service.impl.filehandler.ExcelHandler#parseRow(org.apache.poi.hssf.usermodel.HSSFRow)
	 */
	@Override
	public Object parseRow(HSSFRow hssfRow) {
		String dateStr = null;
		FloatingLoan result = new FloatingLoan();
		try {
			result.setAmount(new Money(getValue(hssfRow, 3)));
			String typeStr = getValue(hssfRow, 2);
			LoanBizSideType type = LoanBizSideType.getByDesc(typeStr);
			if (type == null) {
				throw new CommonException("流贷类型[" + typeStr + "]不能为空");
			} else {
				result.setBizSideType(type);
				dateStr = getValue(hssfRow, 5);
				result.setExpireDate(DateUtil.parseDateNoTime(dateStr,
						"yyyyMMdd"));
				BusinessSide loaner = null;
				String identifier = getValue(hssfRow, 0);
				if (type == LoanBizSideType.CORPORATE) {
					loaner = new Enterprise();
					loaner.setIdentifier(identifier);
				} else if (type == LoanBizSideType.PRIVATE) {
					loaner = new Person();
					loaner.setIdentifier(identifier);
				}
				result.setLoaner(loaner);
				dateStr = getValue(hssfRow, 4);
				result.setReleaseDate(DateUtil.parseDateNoTime(dateStr,
						"yyyyMMdd"));
			}
		} catch (ParseException e) {
			throw new CommonException("解析日期[" + dateStr + "]出错，行号="
					+ hssfRow.getRowNum(), e);
		}
		return result;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {
		for (Object o : dataModels) {
			FloatingLoan loan = (FloatingLoan) o;
			floatingLoanManageService.create(loan);
		}
	}

}
