/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tceon.soa.zauth.client.model.AuthVerifyStrategyConfig;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: IndexController.java, v 0.1 2013-9-18 下午3:47:37
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class IndexController {

	@Autowired
	private AuthVerifyStrategyConfig authVerifyStrategyConfig;

	@RequestMapping("/index.htm")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("authVerifyStrategyConfig",
				authVerifyStrategyConfig);
		return "index";
	}

}
