/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.core.service;

import java.util.Date;

import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.enums.BankBizType;
import com.tccz.tccz.core.model.result.LimitControlResult;

/**
 * 额度核心服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: LimitService.java, v 0.1 2013-9-12 下午2:22:12
 *          narutoying09@gmail.com Exp $
 */
public interface LimitService {
	/**
	 * 计算业务方总授信额度
	 * <ol>
	 * <li>单户（定义：仅个人或法人+关联一家企业）：总额度为1000万</li>
	 * <li>集团（定义：同一法人关联大于等于二家企业 + 法人）：总额度1500万</li>
	 * </ol>
	 * 
	 * @param businessSide
	 * @return
	 */
	Money calculateTotalLimit(BusinessSide businessSide);

	/**
	 * 计算业务方当前占用的授信额度<br/>
	 * 以下业务种类将占用额度：<br/>
	 * <ol>
	 * <li>流贷（个人、企业）</li>
	 * <li>银票（敞口部分）</li>
	 * <li>贴现</li>
	 * </ol>
	 * 注：此处计算时需考虑单户和集团两种情况
	 * 
	 * @param businessSide
	 * @return
	 */
	Money calculateUsedLimit(BusinessSide businessSide);

	/**
	 * 计算业务方当前可用的授信额度（计算结果 = 总额度 - 当前占用额度）
	 * 
	 * @param businessSide
	 * @return
	 */
	Money calculateAvailableLimit(BusinessSide businessSide);

	/**
	 * 计算业务方各类型的业务所占的授信额度
	 * 
	 * @param businessSide
	 * @param type
	 * @return
	 */
	Money calculateDetailLimit(BusinessSide businessSide, BankBizType type);

	/**
	 * 判断新增的金额是否超过该业务方的可用额度
	 * 
	 * @param businessSide
	 *            业务方
	 * @param calDate
	 *            额度计算日期，若为NULL，则默认按照当前日期计算
	 * @param newAmount
	 *            新增金额（与可用金额进行比较）
	 * @return 返回判断结果，包括是否超过额度、总额度、当前可用额度
	 */
	LimitControlResult isOverLimit(BusinessSide businessSide, Date calDate,
			Money newAmount);

}
