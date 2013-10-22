/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.impl.filehandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.ParaCheckUtil;
import com.tccz.tccz.common.util.exception.CommonException;
import com.tccz.tccz.common.util.template.CommonManageTemplate;
import com.tccz.tccz.common.util.template.callback.CommonManageCallback;
import com.tccz.tccz.core.service.util.FileHandler;
import com.tccz.tccz.core.service.util.FileHandlerFactory;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ExcelHandler.java, v 0.1 2013-10-22 上午9:19:40
 *          narutoying09@gmail.com Exp $
 */
public abstract class ExcelHandler implements FileHandler, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(ExcelHandler.class);

	@Autowired
	private FileHandlerFactory fileHandlerFactory;

	@Autowired
	private CommonManageTemplate commonManageTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		registerHandler();
	}

	/**
	 * @see com.tccz.tccz.core.service.util.FileHandler#handle(java.io.InputStream)
	 */
	@Override
	public CommonResult handle(final InputStream inputStream) {
		final CommonResult result = new CommonResult();
		commonManageTemplate.manageWithTransaction(result,
				new CommonManageCallback() {

					@Override
					public void doManage() {
						List<Object> dataList = new ArrayList<Object>(); // 存放excel解析后的
						HSSFWorkbook hssfWorkbook;
						try {
							hssfWorkbook = new HSSFWorkbook(inputStream);
							// 目前只处理1个Sheet
							HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
							// 默认第一行为标题，直接从第二行开始解析
							for (int rowNum = 1; rowNum <= hssfSheet
									.getLastRowNum(); rowNum++) {
								HSSFRow hssfRow = hssfSheet.getRow(rowNum);
								if (hssfRow == null) {
									continue;
								} else {
									dataList.add(parseRow(hssfRow));
								}
							}
							dealDataModels(dataList);
							CommonResult.buildResult(result, true,
									"接收并处理报表文件成功");
						} catch (IOException e) {
							logger.error("", e);
						}

						CommonResult.buildResult(result, true, "文件处理成功");
					}

					@Override
					public void checkParameter() {
						ParaCheckUtil.checkParaNotNull(inputStream);
					}
				});
		return result;
	}

	/**
	 * 解析一行数据
	 * 
	 * @param hssfRow
	 */
	public abstract Object parseRow(HSSFRow hssfRow);

	/**
	 * 处理解析后得到的数据模型
	 * 
	 * @param dataModels
	 */
	public abstract void dealDataModels(List<Object> dataModels);

	protected String getValue(HSSFRow hssfRow, int colNum) {
		return getValue(hssfRow.getCell(colNum), hssfRow.getRowNum(), colNum);
	}

	@SuppressWarnings("static-access")
	protected String getValue(HSSFCell hssfCell, int rowNum, int colNum) {
		// TODO 针对不同类型需要区别处理
		try {
			if (hssfCell != null) {
				hssfCell.setCellType(hssfCell.CELL_TYPE_STRING);
				return hssfCell.getStringCellValue();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new CommonException(
					"获取指定列值出错[" + rowNum + "," + colNum + "]", e);
		}
		/*
		 * if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) { //
		 * 返回布尔类型的值 return String.valueOf(hssfCell.getBooleanCellValue()); }
		 * else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) { //
		 * 返回数值类型的值 return String.valueOf(hssfCell.getNumericCellValue()); }
		 * else { // 返回字符串类型的值 return
		 * String.valueOf(hssfCell.getStringCellValue()); }
		 */
	}

	@Override
	public void registerHandler() {
		fileHandlerFactory.registerFileHandler(this);
	}

	public void setFileHandlerFactory(FileHandlerFactory fileHandlerFactory) {
		this.fileHandlerFactory = fileHandlerFactory;
	}
}
