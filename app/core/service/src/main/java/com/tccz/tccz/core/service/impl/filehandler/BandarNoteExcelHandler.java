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
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FullMarginBandarNote;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.OpenBandarNote;
import com.tccz.tccz.core.model.enums.BandarNoteType;
import com.tccz.tccz.core.service.manage.BandarNoteManageService;
import com.tccz.tccz.core.service.util.FileHandlerNames;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteExcelHandler.java, v 0.1 2013-10-22 下午4:20:40
 *          narutoying09@gmail.com Exp $
 */
public class BandarNoteExcelHandler extends ExcelHandler {

	@Autowired
	private BandarNoteManageService bandarNoteManageService;

	@Override
	public String getHandlerName() {
		return FileHandlerNames.BANDAR_NOTE;
	}

	/**
	 * excel表格说明
	 * <ol>
	 * <li>0 银票号</li>
	 * <li>1 企业机构号</li>
	 * <li>2 企业名</li>
	 * <li>3 开票日</li>
	 * <li>4 到期日</li>
	 * <li>5 银票类型</li>
	 * <li>6 票面金额</li>
	 * <li>7 保证金金额</li>
	 * <li>8 敞口金额</li>
	 * <li>9 封敞口金额</li>
	 * </ol>
	 * 
	 * @see com.tccz.tccz.core.service.impl.filehandler.ExcelHandler#parseRow(org.apache.poi.hssf.usermodel.HSSFRow)
	 */
	@Override
	public Object parseRow(HSSFRow hssfRow) {
		BandarNote result = null;
		String dateStr = null;
		String typeStr = getValue(hssfRow, 5);
		BandarNoteType type = BandarNoteType.getByDesc(typeStr);
		if (type == null) {
			throw new CommonException("不支持的银票类型【" + typeStr + "】");
		} else {
			try {
				if (type == BandarNoteType.FULL_MARGIN) {
					FullMarginBandarNote tmp = new FullMarginBandarNote();
					result = tmp;
				} else if (type == BandarNoteType.OPEN) {
					OpenBandarNote tmp = new OpenBandarNote();
					tmp.setOpenMoney(new Money(getValue(hssfRow, 8)));
					tmp.setCloseMoney(new Money(getValue(hssfRow, 9)));
					result = tmp;
				}
				result.setType(type);
				result.setAmount(new Money(getValue(hssfRow, 6)));
				result.setMargin(new Money(getValue(hssfRow, 7)));
				dateStr = getValue(hssfRow, 3);
				result.setDrawDate(DateUtil.parseDateNoTime(dateStr,
						"yyyy-MM-dd"));
				Enterprise drawer = new Enterprise();
				drawer.setIdentifier(getValue(hssfRow, 1));
				result.setDrawer(drawer);
				result.setExpireDate(DateUtil.parseDateNoTime(
						getValue(hssfRow, 4), "yyyy-MM-dd"));
				result.setNumber(getValue(hssfRow, 0));
			} catch (ParseException e) {
				throw new CommonException("解析日期[" + dateStr + "]出错", e);
			}
		}
		return result;
	}

	@Override
	public void dealDataModels(List<Object> dataModels) {
		for (Object o : dataModels) {
			BandarNote data = (BandarNote) o;
			bandarNoteManageService.create(data);
		}
	}

}
