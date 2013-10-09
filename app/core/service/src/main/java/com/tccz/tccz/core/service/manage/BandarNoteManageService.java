/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.manage;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.core.model.BandarNote;

/**
 * 银票操作服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BandarNoteManageService.java, v 0.1 2013-9-13 下午4:32:01
 *          narutoying09@gmail.com Exp $
 */
public interface BandarNoteManageService {

	/**
	 * 创建银票
	 * 
	 * @param BandarNote
	 * @return
	 */
	CommonResult create(BandarNote bandarNote);

	/**
	 * 删除银票
	 * 
	 * @param BandarNoteId
	 * @return
	 */
	CommonResult delete(int bandarNoteId);

	/**
	 * 更新银票
	 * 
	 * @param BandarNote
	 * @return
	 */
	CommonResult update(BandarNote bandarNote);

}
