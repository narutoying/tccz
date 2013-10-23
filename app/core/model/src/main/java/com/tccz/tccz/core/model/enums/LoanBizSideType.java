/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 贷款业务方类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LoanType.java, v 0.1 2013-9-12 上午11:21:12
 *          narutoying09@gmail.com Exp $
 */
public enum LoanBizSideType {
	/** 对公 */
	CORPORATE("CORPORATE", "对公"),
	/** 对私 */
	PRIVATE("PRIVATE", "对私");
	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private LoanBizSideType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static LoanBizSideType getByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (LoanBizSideType mode : values()) {
				if (StringUtils.equals(code, mode.getCode())) {
					return mode;
				}
			}
		}
		return null;
	}

	public static LoanBizSideType getByDesc(String content) {
		if (StringUtils.isNotBlank(content)) {
			for (LoanBizSideType mode : values()) {
				if (StringUtils.equals(content, mode.getDescription())) {
					return mode;
				}
			}
		}
		return null;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (LoanBizSideType LoanBizSideType : values()) {
			map.put(LoanBizSideType.getCode(), LoanBizSideType.getDescription());
		}
		return map;
	}

	public static List<Map<String, String>> toList() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (LoanBizSideType LoanBizSideType : values()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", LoanBizSideType.getCode());
			map.put("desc", LoanBizSideType.getDescription());
			result.add(map);
		}
		return result;
	}
}
