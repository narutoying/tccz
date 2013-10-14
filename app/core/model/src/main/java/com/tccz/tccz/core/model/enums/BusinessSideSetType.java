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
 * 业务方集合类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BusinessSideSetType.java, v 0.1 2013-10-14 下午4:19:46
 *          narutoying09@gmail.com Exp $
 */
public enum BusinessSideSetType {
	SINGLE("SINGLE", "单户"), UNITED("UNITED", "集团");

	private BusinessSideSetType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static BusinessSideSetType getByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (BusinessSideSetType mode : values()) {
				if (StringUtils.equals(code, mode.getCode())) {
					return mode;
				}
			}
		}
		return null;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BusinessSideSetType BusinessSideSetType : values()) {
			map.put(BusinessSideSetType.getCode(),
					BusinessSideSetType.getDescription());
		}
		return map;
	}

	public static List<Map<String, String>> toList() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (BusinessSideSetType BusinessSideSetType : values()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", BusinessSideSetType.getCode());
			map.put("desc", BusinessSideSetType.getDescription());
			result.add(map);
		}
		return result;
	}

}