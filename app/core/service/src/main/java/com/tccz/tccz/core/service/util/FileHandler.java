/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.util;

import java.io.InputStream;

import com.tccz.tccz.common.util.CommonResult;

/**
 * 文件处理器
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FileHandler.java, v 0.1 2013-10-22 上午9:07:54
 *          narutoying09@gmail.com Exp $
 */
public interface FileHandler {

	CommonResult handle(InputStream inputStream);

	String getHandlerName();

	void registerHandler();
}
