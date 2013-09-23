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
 * 贴现状态
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountState.java, v 0.1 2013-9-12 下午1:55:08
 *          narutoying09@gmail.com Exp $
 */
public enum DiscountState {
	/** 在库 */
	IN_STORE("IN_STORE", "在库"),
	/** 转贴现 */
	TRANSFER("TRANSFER", "转贴现"),
	/** 再贴现 */
	RE_DISCOUNT("RE_DISCOUNT", "再贴现"),
	/** 已回购 */
	BUY_BACK("BUY_BACK", "已回购"),
	/** 已托收 */
	COLLECTED("COLLECTED", "已托收");

	private DiscountState(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static DiscountState getByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (DiscountState mode : values()) {
				if (StringUtils.equals(code, mode.getCode())) {
					return mode;
				}
			}
		}
		return null;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (DiscountState DiscountState : values()) {
			map.put(DiscountState.getCode(), DiscountState.getDescription());
		}
		return map;
	}

	public static List<Map<String, String>> toList() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (DiscountState DiscountState : values()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("stateCode", DiscountState.getCode());
			map.put("stateDesc", DiscountState.getDescription());
			result.add(map);
		}
		return result;
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
}
