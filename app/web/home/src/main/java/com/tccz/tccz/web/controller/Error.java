/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: Error.java, v 0.1 2013-10-29 下午2:26:40 narutoying09@gmail.com
 *          Exp $
 */
@Controller
public class Error {
	@RequestMapping("accessDenied.htm")
	public String accessDenied() {
		return "accessDenied";
	}
}
