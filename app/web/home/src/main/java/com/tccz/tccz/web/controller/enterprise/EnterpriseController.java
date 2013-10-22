/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.enterprise;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.service.manage.BusinessSideManageService;
import com.tccz.tccz.core.service.query.BusinessSideQueryService;
import com.tccz.tccz.core.service.util.FileHandlerFactory;
import com.tccz.tccz.core.service.util.FileHandlerNames;
import com.tccz.tccz.web.enums.OperationType;
import com.tccz.tccz.web.util.JSONUtil;
import com.tccz.tccz.web.util.WebPageCallback;
import com.tccz.tccz.web.util.WebUtil;

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

	@Autowired
	private BusinessSideManageService businessSideManageService;

	@Autowired
	private FileHandlerFactory fileHandlerFactory;

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

	@RequestMapping("/update/enterprise/add.htm")
	public String goAdd(ModelMap modelMap) {
		initModelMap(modelMap, OperationType.ADD, null);
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/enterprise/add.htm", method = RequestMethod.POST)
	public String doAdd(ModelMap modelMap, EnterpriseForm form) {
		CommonResult result = businessSideManageService.createEnterprise(form
				.convertToDomain());
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

	private String buildQueryIndexRedirectHtmUrl() {
		return "redirect:/query/enterprise/index.htm";
	}

	@RequestMapping("/update/enterprise/delete.htm")
	public String delete(ModelMap modelMap, String identifier) {
		CommonResult result = businessSideManageService
				.deleteEnterprise(identifier);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

	@RequestMapping("/update/enterprise/modify.htm")
	public String goModify(ModelMap modelMap, String identifier) {
		initModelMap(modelMap, OperationType.UPDATE,
				businessSideQueryService
						.queryEnterpriseByInstitudeCode(identifier));
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/enterprise/modify.htm", method = RequestMethod.POST)
	public String doModify(ModelMap modelMap, EnterpriseForm form) {
		CommonResult result = businessSideManageService.updateEnterprse(form
				.convertToDomain());
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

	@RequestMapping("/query/enterprise/view.htm")
	public String view(ModelMap modelMap, String identifier) {
		initModelMap(modelMap, OperationType.QUERY,
				businessSideQueryService
						.queryEnterpriseByInstitudeCode(identifier));
		return PREFIX + "one";
	}

	@RequestMapping("/update/enterprise/associatePerson.htm")
	public String goAssociatePerson(ModelMap modelMap, String identifier) {
		modelMap.addAttribute("item", businessSideQueryService
				.queryEnterpriseByInstitudeCode(identifier));
		return PREFIX + "associate";
	}

	@RequestMapping(value = "/update/enterprise/associatePerson.htm", method = RequestMethod.POST)
	public String doAssociatePerson(ModelMap modelMap,
			final String enterpriseId, String personId) {
		CommonResult result = businessSideManageService
				.associatePersonToEnterprise(enterpriseId, personId);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildAssociateRedirectHtmUrl(enterpriseId);
			}
		});
	}

	protected String buildAssociateRedirectHtmUrl(String identifier) {
		return "redirect:/update/enterprise/associatePerson.htm?identifier="
				+ identifier;
	}

	@RequestMapping("/update/enterprise/deleteAssociatePerson.htm")
	public String deleteAssociatePerson(ModelMap modelMap,
			final String enterpriseId, String personId) {
		CommonResult result = businessSideManageService
				.deleteAssociatePersonToEnterprise(enterpriseId, personId);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildAssociateRedirectHtmUrl(enterpriseId);
			}
		});
	}

	private void initModelMap(ModelMap modelMap, OperationType operationType,
			Enterprise item) {
		modelMap.addAttribute(OperationType.OPERATION, operationType.getCode());
		modelMap.addAttribute(OperationType.OPERATION_DESC,
				operationType.getDescription());
		if (item != null) {
			modelMap.addAttribute("item", JSONSerializer.toJSON(item));
		}
	}

	@RequestMapping(value = "/update/enterprise/uploadEnterpriseInfo.htm", method = RequestMethod.POST)
	public String uploadEnterpriseInfo(
			@RequestParam(value = "file", required = false) MultipartFile file,
			ModelMap modelMap) throws IOException {
		InputStream inputStream = file.getInputStream();
		CommonResult result = fileHandlerFactory.getFileHandler(
				FileHandlerNames.ENTERPRISE).handle(inputStream);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

}
