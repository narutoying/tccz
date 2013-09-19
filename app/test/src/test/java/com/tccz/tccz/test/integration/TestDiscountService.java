/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.tccz.tccz.test.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;
import com.tccz.tccz.core.service.query.DiscountQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: TestContactService.java, v 0.1 2013-4-9 下午9:16:10
 *          narutoying09@gmail.com Exp $
 */
public class TestDiscountService extends BaseTestCase {

	@Autowired
	private DiscountQueryService discountQueryService;

	@Test
	public void test() {
		DiscountQueryCondition condition = new DiscountQueryCondition();
		condition.setBandarNoteNumber("123");
		condition.setEnterpriseName("");
//		condition.setDoPage(true);
		condition.setPageSize(1);
		PageList<Discount> queryByCondition = discountQueryService
				.queryByCondition(condition);
		System.out.println(queryByCondition.getDataList().size());
	}
}
