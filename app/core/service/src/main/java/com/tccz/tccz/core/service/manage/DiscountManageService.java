/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service.manage;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.core.model.Discount;

/**
 * 贴现操作服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountManageService.java, v 0.1 2013-9-13 下午4:32:01
 *          narutoying09@gmail.com Exp $
 */
public interface DiscountManageService {

	/**
	 * 创建贴现
	 * 
	 * @param discount
	 * @return
	 */
	CommonResult createDiscount(Discount discount);

	/**
	 * 删除贴现
	 * 
	 * @param discountId
	 * @return
	 */
	CommonResult deleteDiscount(int discountId);

	/**
	 * 更新贴现 1. 更新贴现本身数据（对于状态要做好校验工作，如到期日期要<=操作时当前日期才允许修改为“已托收”） 2. 创建贴现变更历史
	 * 暂不允许修改金额！
	 * 
	 * @param discount
	 * @return
	 */
	CommonResult updateDiscount(Discount discount);

}
