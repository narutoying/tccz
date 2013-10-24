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
 * 银票类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteType.java, v 0.1 2013-9-12 上午11:21:12
 *          narutoying09@gmail.com Exp $
 */
public enum BandarNoteType {
	/** 全额保证金 */
	FULL_MARGIN("FULL_MARGIN", "全额保证金"),
	/** 敞口 */
	OPEN("OPEN", "敞口");
	private BandarNoteType(String code, String description) {
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

	public static BandarNoteType getByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (BandarNoteType mode : values()) {
				if (StringUtils.equals(code, mode.getCode())) {
					return mode;
				}
			}
		}
		return null;
	}

	public static BandarNoteType getByDesc(String value) {
		if (StringUtils.isNotBlank(value)) {
			for (BandarNoteType mode : values()) {
				if (StringUtils.equals(value, mode.getDescription())) {
					return mode;
				}
			}
		}
		return null;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BandarNoteType BandarNoteType : values()) {
			map.put(BandarNoteType.getCode(), BandarNoteType.getDescription());
		}
		return map;
	}

	public static List<Map<String, String>> toList() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (BandarNoteType BandarNoteType : values()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", BandarNoteType.getCode());
			map.put("desc", BandarNoteType.getDescription());
			result.add(map);
		}
		return result;
	}
}
