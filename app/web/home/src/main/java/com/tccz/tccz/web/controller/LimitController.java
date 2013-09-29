/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.enums.LimitType;
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

	@Autowired
	private LimitService limitService;

	@RequestMapping(value = "/query/limit/queryForEnterprise.json", method = RequestMethod.GET)
	public void indexJson(HttpServletResponse res, ModelMap map, Integer id) {
		BusinessSide businessSide = new Enterprise();
		businessSide.setId(id);
		JSONUtil.writeBackJsonWithConfig(res, limitService.calculateLimit(
				businessSide, null, LimitType.AVAILABLE));
	}
}
