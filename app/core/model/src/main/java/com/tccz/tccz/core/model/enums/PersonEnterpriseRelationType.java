/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.model.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 个人与企业间的关系类型
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PersonEnterpriseRelationType.java, v 0.1 2013-10-16 下午2:06:26
 *          narutoying09@gmail.com Exp $
 */
public enum PersonEnterpriseRelationType {
	/** 法人 */
	LEGAL,
	/** 普通关系，现泛指非法人，以后视情况细化 */
	NORMAL;

	public static PersonEnterpriseRelationType getByCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (PersonEnterpriseRelationType mode : values()) {
				if (StringUtils.equals(code, mode.toString())) {
					return mode;
				}
			}
		}
		return null;
	}
}
