/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.manage;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.core.model.FloatingLoan;

/**
 * 流贷操作服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: FloatingLoanManageService.java, v 0.1 2013-9-13 下午4:32:01
 *          narutoying09@gmail.com Exp $
 */
public interface FloatingLoanManageService {

	/**
	 * 创建流贷
	 * 
	 * @param FloatingLoan
	 * @return
	 */
	CommonResult create(FloatingLoan floatingLoan);

	/**
	 * 删除流贷
	 * 
	 * @param floatingLoan
	 * @return
	 */
	CommonResult delete(int floatingLoanId);

	/**
	 * 更新流贷
	 * 
	 * @param FloatingLoan
	 * @return
	 */
	CommonResult update(FloatingLoan floatingLoan);

}
