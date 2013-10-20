/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.limit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tccz.tccz.biz.service.LimitBizService;
import com.tccz.tccz.common.util.PageUtil;
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.web.util.JSONUtil;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitController.java, v 0.1 2013-9-29 下午2:31:28
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class LimitController {

	private static final String QUERY_HTM_PREFIX = "/query/limit/";
	private static final String PREFIX = "limit/";

	@Autowired
	private LimitService limitService;

	@Autowired
	private LimitBizService limitBizService;

	@RequestMapping(value = QUERY_HTM_PREFIX + "queryForEnterprise.json", method = RequestMethod.GET)
	public void queryForEnterprise(HttpServletResponse res, ModelMap map,
			String identifier) {
		BusinessSide businessSide = new Enterprise();
		businessSide.setIdentifier(identifier);
		JSONUtil.writeBackJsonWithConfig(res,
				limitService.calculateAvailableLimit(businessSide));
	}

	@RequestMapping(value = QUERY_HTM_PREFIX + "queryForPerson.json", method = RequestMethod.GET)
	public void queryForPerson(HttpServletResponse res, ModelMap map,
			String identifier) {
		BusinessSide businessSide = new Person();
		businessSide.setIdentifier(identifier);
		JSONUtil.writeBackJsonWithConfig(res,
				limitService.calculateAvailableLimit(businessSide));
	}

	@RequestMapping(value = QUERY_HTM_PREFIX + "viewEnterprise.htm", method = RequestMethod.GET)
	public String viewEnterprise(ModelMap map, String enterpriseName,
			Integer currentPage, Integer pageSize) {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = PageUtil.PAGE_SIZE;
		}
		map.addAttribute("enterpriseName", enterpriseName);
		map.addAttribute("page", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("views", limitBizService.queryEnterpriseLimitViews(
				enterpriseName, currentPage, pageSize));
		return PREFIX + "enterprise";
	}

	@RequestMapping(value = QUERY_HTM_PREFIX + "viewPerson.htm", method = RequestMethod.GET)
	public String viewPerson(ModelMap map, String personName,
			Integer currentPage, Integer pageSize) {
		if (currentPage == null) {
			currentPage = 1;
		}
		if (pageSize == null) {
			pageSize = PageUtil.PAGE_SIZE;
		}
		map.addAttribute("personName", personName);
		map.addAttribute("page", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("views", limitBizService.queryPersonLimitViews(
				personName, currentPage, pageSize));
		return PREFIX + "person";
	}

}
