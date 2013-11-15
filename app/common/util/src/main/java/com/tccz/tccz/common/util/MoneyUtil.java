/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.common.util;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: MoneyUtil.java, v 0.1 2013-11-14 下午4:08:41
 *          narutoying09@gmail.com Exp $
 */
public class MoneyUtil {
	public static String parseMoney(String str1) {
		int DEF_DIV_SCALE = 3;
		int len = str1.length();
		int k = 0;
		int m = 0;
		int dot = 0;
		int start = 0;
		String str_dot = "";
		String str_start = "";
		for (k = 1; k < len; k++) {
			if (str1.substring(k, k + 1).equals(".")) {
				dot = k + 1;
				break;
			}
		}
		start = (dot - 1) % DEF_DIV_SCALE;
		if (start == 0) {
			str_start = "";
		} else {
			str_start = str1.substring(0, start) + ",";
		}
		m = 0;
		for (k = start; k < dot; k += DEF_DIV_SCALE) {
			str_start += str1.substring(k, k + DEF_DIV_SCALE) + ",";
			m++;
		}
		if (start == 0) {
			str_start = str_start.substring(0, dot + m - 3);
		} else {
			str_start = str_start.substring(0, dot + m - 2);
		}
		str_dot = str1.substring(dot, len);
		str1 = str_start + "." + str_dot;

		return str1;
	}
}
