/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.manage;

import java.util.List;

import com.tccz.tccz.common.util.CommonResult;

/**
 * 数据导出服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DataExportService.java, v 0.1 2013-12-17 下午1:31:59
 *          narutoying09@gmail.com Exp $
 */
public interface DataExportService<T> {

	/**
	 * 将数据导出为excel文件
	 * 
	 * @param dataList
	 * @param config
	 * @return
	 */
	CommonResult exportExcel(List<T> dataList);
}
