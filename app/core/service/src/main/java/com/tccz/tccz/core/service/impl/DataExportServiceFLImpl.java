/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.util.CollectionUtils;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.DateUtil;
import com.tccz.tccz.common.util.MoneyUtil;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.enums.BankBizType;
import com.tccz.tccz.core.service.manage.DataExportService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DataExportServiceFLImpl.java, v 0.1 2013-12-17 下午3:54:59
 *          narutoying09@gmail.com Exp $
 */
public class DataExportServiceFLImpl implements DataExportService<FloatingLoan> {

	@Override
	public CommonResult exportExcel(List<FloatingLoan> dataList) {
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建Excel的工作sheet,对应到一个excel文档的tab
		HSSFSheet sheet = wb.createSheet("sheet0");
		// 设置excel每列宽度
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 3500);
		// 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置边框
		style.setBottomBorderColor(HSSFColor.RED.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 设置字体
		HSSFFont font = wb.createFont();
		font.setFontName("Verdana");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 300);
		font.setColor(HSSFColor.BLUE.index);
		style.setFont(font);
		// 生成标题行 TODO 抽取成方法
		buildTitleRow(sheet, style);
		if (!CollectionUtils.isEmpty(dataList)) {
			HSSFCellStyle style1 = wb.createCellStyle();
			style1.setWrapText(true);// 自动换行
			// 按行生成业务数据
			int rowNum = 1;
			for (FloatingLoan item : dataList) {
				HSSFRow row = sheet.createRow(rowNum);
				row.setHeight((short) 500);
				// 每个字段一个单元格 TODO 抽取成方法
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(style1);
				cell0.setCellValue(item.getLoaner().getName());
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(style1);
				cell1.setCellValue(MoneyUtil.parseMoney(item.getAmount()
						.toString()));
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(style1);
				cell2.setCellValue(item.getReleaseDate());
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(style1);
				cell3.setCellValue(item.getExpireDate());
				rowNum++;
			}
		}

		FileOutputStream os;
		try {
			os = new FileOutputStream(exportExcelFileName());
			wb.write(os);
			os.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		CommonResult result = new CommonResult();
		CommonResult.buildResult(result, true, "");
		return result;
	}

	/**
	 * 
	 * 
	 * @param sheet
	 */
	private void buildTitleRow(HSSFSheet sheet, HSSFCellStyle style) {
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 600);
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellStyle(style);
		cell0.setCellValue("借款人");
		HSSFCell cell1 = row.createCell(1);
		cell1.setCellStyle(style);
		cell1.setCellValue("贷款余额");
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellStyle(style);
		cell2.setCellValue("发放日");
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellStyle(style);
		cell3.setCellValue("到期日");
	}

	protected String exportExcelFileName() {
		return BankBizType.FLOATING_LOAN.getCode() + "_"
				+ DateUtil.getDateString(new Date()) + ".xls";
	}

}
