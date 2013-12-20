/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.manage.job;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.query.FloatingLoanQueryCondition;
import com.tccz.tccz.core.service.manage.DataExportService;
import com.tccz.tccz.core.service.query.FloatingLoanQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DataExportJob.java, v 0.1 2013-12-12 下午5:11:03
 *          narutoying09@gmail.com Exp $
 */
public class FloatingLoanExportJob implements DataExportJob {

	private static final Logger logger = LoggerFactory
			.getLogger(FloatingLoanExportJob.class);

	@Autowired
	private DataExportService<FloatingLoan> flExportService;

	@Autowired
	private FloatingLoanQueryService floatingLoanQueryService;

	public void export() {
		flExportService.exportExcel(floatingLoanQueryService.queryByCondition(
				new FloatingLoanQueryCondition()).getDataList());
	}

}
