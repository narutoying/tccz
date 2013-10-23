/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.data.io;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.StringUtil;
import com.tccz.tccz.core.service.util.FileHandlerFactory;
import com.tccz.tccz.core.service.util.FileHandlerNames;
import com.tccz.tccz.web.util.WebPageCallback;
import com.tccz.tccz.web.util.WebUtil;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DataImportController.java, v 0.1 2013-10-22 下午4:15:17
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class DataImportController {

	/** 流贷 */
	private static final String FLOATING_LOAN = "FLOATING_LOAN";
	/** 银票 */
	private static final String BANDAR_NOTE = "BANDAR_NOTE";
	/** 贴现 */
	private static final String DISCOUNT = "DISCOUNT";

	@Autowired
	private FileHandlerFactory fileHandlerFactory;

	@RequestMapping(value = "/update/data/index.htm", method = RequestMethod.GET)
	public String goUploadAccountData(ModelMap modelMap) {
		return "data/manage";
	}

	/**
	 * 上传台账数据（统一门面）
	 * 
	 * @param file
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/update/data/uploadAccountData.htm", method = RequestMethod.POST)
	public String doUploadAccountData(MultipartFile file,
			final ModelMap modelMap, String bizType) throws IOException {
		InputStream inputStream = file.getInputStream();
		String handlerName = null;
		if (StringUtil.equals(bizType, FLOATING_LOAN)) {
			handlerName = FileHandlerNames.FLOATING_LOAN;
		} else if (StringUtil.equals(bizType, BANDAR_NOTE)) {
			handlerName = FileHandlerNames.BANDAR_NOTE;
		} else if (StringUtil.equals(bizType, DISCOUNT)) {
			handlerName = FileHandlerNames.DISCOUNT;
		}
		final CommonResult result = fileHandlerFactory.getFileHandler(
				handlerName).handle(inputStream);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				modelMap.addAttribute("result", result.getResultMessage());
				return goUploadAccountData(modelMap);
			}

		});
	}
}
