/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.enterprise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;
import com.tccz.tccz.web.util.JSONUtil;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: EnterpriseController.java, v 0.1 2013-9-23 上午11:18:25
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class EnterpriseController {

	private static final String PREFIX = "enterprise/";

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@RequestMapping("/query/enterprise/fuzzyQuery.json")
	public void fuzzyQuery(String fuzzyName, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Enterprise> enterprises = businessSideQueryService
				.fuzzyQueryEnterprises(fuzzyName);
		resultMap.put("totalItems", enterprises.size());
		resultMap.put("items", enterprises);
		JSONUtil.writeBackJsonWithConfig(response, resultMap);
	}

	@RequestMapping("/query/enterprise/index.htm")
	public String index(ModelMap modelMap, String fuzzyName,
			Integer currentPage, Integer pageSize) {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = PageUtil.PAGE_SIZE;
		}
		modelMap.addAttribute("pageList", businessSideQueryService
				.fuzzyQueryEnterprisesPage(fuzzyName, currentPage, pageSize));
		modelMap.addAttribute("fuzzyName", fuzzyName);
		return PREFIX + "index";
	}
}
