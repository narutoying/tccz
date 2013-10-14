/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.tccz.tccz.test.integration;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccz.tccz.common.dal.daointerface.DiscountDAO;
import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;
import com.tccz.tccz.core.service.LimitService;
import com.tccz.tccz.core.service.manage.DiscountManageService;
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

	@Autowired
	private DiscountManageService discountManageService;

	@Autowired
	private LimitService limitService;

	@Autowired
	private DiscountDAO discountDAO;

	public void testQuery() {
		DiscountQueryCondition condition = new DiscountQueryCondition();
		condition.setBandarNoteNumber("123");
		condition.setEnterpriseName("");
		condition.setDoPage(true);
		condition.setLimit(1);
		PageList<Discount> queryByCondition = discountQueryService
				.queryByCondition(condition);
		System.out.println(queryByCondition.getDataList().size());
	}

	@Test
	public void testCreate() {
		// List<DiscountDO> byExpireDate = discountDAO.getByExpireDate(1,
		// new Date(), null);
		// System.out.println(byExpireDate);
		Enterprise proposer = new Enterprise(1, "香塘");
		System.out.println("可用额度："
				+ limitService.calculateAvailableLimit(proposer));
		Discount discount = new Discount();
		discount.setAmount(new Money("9999200"));
		discount.setBandarNoteNumber("123456");
		discount.setExpireDate(new Date());
		discount.setProposer(proposer);
		discount.setState(DiscountState.IN_STORE);
		CommonResult commonResult = discountManageService
				.createDiscount(discount);
		System.out.println(commonResult);
	}
}
