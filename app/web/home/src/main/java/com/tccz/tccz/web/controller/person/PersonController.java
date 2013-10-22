/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.person;

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
import com.tccz.tccz.core.model.Person;
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
public class PersonController {

	private static final String PREFIX = "person/";

	@Autowired
	private BusinessSideQueryService businessSideQueryService;

	@Autowired
	private BusinessSideManageService businessSideManageService;

	@Autowired
	private FileHandlerFactory fileHandlerFactory;

	@RequestMapping("/query/person/fuzzyQuery.json")
	public void fuzzyQuery(String fuzzyName, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Person> enterprises = businessSideQueryService
				.fuzzyQueryPersons(fuzzyName);
		resultMap.put("totalItems", enterprises.size());
		resultMap.put("items", enterprises);
		JSONUtil.writeBackJsonWithConfig(response, resultMap);
	}

	@RequestMapping("/query/person/index.htm")
	public String index(ModelMap modelMap, String fuzzyName,
			Integer currentPage, Integer pageSize) {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = PageUtil.PAGE_SIZE;
		}
		modelMap.addAttribute("pageList", businessSideQueryService
				.fuzzyQueryPersonsPage(fuzzyName, currentPage, pageSize));
		modelMap.addAttribute("fuzzyName", fuzzyName);
		return PREFIX + "index";
	}

	@RequestMapping("/update/person/add.htm")
	public String goAdd(ModelMap modelMap) {
		initModelMap(modelMap, OperationType.ADD, null);
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/person/add.htm", method = RequestMethod.POST)
	public String doAdd(ModelMap modelMap, PersonForm form) {
		CommonResult result = businessSideManageService.createPerson(form
				.convertToDomain());
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

	private String buildQueryIndexRedirectHtmUrl() {
		return "redirect:/query/person/index.htm";
	}

	@RequestMapping("/update/person/delete.htm")
	public String delete(ModelMap modelMap, String identifier) {
		CommonResult result = businessSideManageService
				.deletePerson(identifier);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

	@RequestMapping("/update/person/modify.htm")
	public String goModify(ModelMap modelMap, String identifier) {
		initModelMap(modelMap, OperationType.UPDATE,
				businessSideQueryService.queryPersonByIdCard(identifier, false));
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/person/modify.htm", method = RequestMethod.POST)
	public String doModify(ModelMap modelMap, PersonForm form) {
		CommonResult result = businessSideManageService.updatePerson(form
				.convertToDomain());
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}

	@RequestMapping("/query/person/view.htm")
	public String view(ModelMap modelMap, String identifier) {
		initModelMap(modelMap, OperationType.QUERY,
				businessSideQueryService.queryPersonByIdCard(identifier, false));
		return PREFIX + "one";
	}

	private void initModelMap(ModelMap modelMap, OperationType operationType,
			Person item) {
		modelMap.addAttribute(OperationType.OPERATION, operationType.getCode());
		modelMap.addAttribute(OperationType.OPERATION_DESC,
				operationType.getDescription());
		if (item != null) {
			modelMap.addAttribute("item", JSONSerializer.toJSON(item));
		}
	}

	@RequestMapping(value = "/update/person/uploadPersonInfo.htm", method = RequestMethod.POST)
	public String uploadPersonInfo(
			@RequestParam(value = "file", required = false) MultipartFile file,
			ModelMap modelMap) throws IOException {
		InputStream inputStream = file.getInputStream();
		CommonResult result = fileHandlerFactory.getFileHandler(
				FileHandlerNames.PERSON).handle(inputStream);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl();
			}
		});
	}
}
