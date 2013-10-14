/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.tccz.tccz.test.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.result.LimitControlResult;
import com.tccz.tccz.core.service.LimitService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: TestContactService.java, v 0.1 2013-4-9 下午9:16:10
 *          narutoying09@gmail.com Exp $
 */
public class TestLimitService extends BaseTestCase {

	@Autowired
	private LimitService limitService;

	@Test
	public void test() {
		// 1. 计算额度
		BusinessSide businessSide = new Enterprise();
		businessSide.setId(1);
		Money calculateLimit = limitService
				.calculateAvailableLimit(businessSide);
		System.out.println(calculateLimit);
		// 2. 控制额度
		LimitControlResult controlResult = limitService.isOverLimit(
				businessSide, null, new Money("8000000.00"));
		System.out.println(controlResult);
	}
}
