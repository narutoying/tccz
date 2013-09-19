/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.discount;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tccz.tccz.common.util.DateUtil;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;
import com.tccz.tccz.core.service.query.DiscountQueryService;
import com.tccz.tccz.web.util.JSONUtil;

/**
 * 贴现
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountController.java, v 0.1 2013-9-17 下午4:48:03
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class DiscountController {

	@Autowired
	private DiscountQueryService discountQueryService;

	private static final String PREFIX = "discount/";

	@RequestMapping("/query/discount/index.htm")
	public String index(ModelMap modelMap, DiscountQueryCondition condition) {
		PageList<Discount> queryByCondition = discountQueryService
				.queryByCondition(condition);
		modelMap.addAttribute("pageList", queryByCondition);
		modelMap.addAttribute("queryCondition", condition);
		return PREFIX + "index";
	}

	@RequestMapping(value = "/query/discount/index.json", method = RequestMethod.GET)
	public void indexJson(HttpServletResponse res, ModelMap map,
			DiscountQueryCondition condition) {
		PageList<Discount> queryByCondition = discountQueryService
				.queryByCondition(condition);
		List<Discount> dataList = queryByCondition.getDataList();
		JSONUtil.writeBackJsonWithConfig(res,
				buildDiscountPageResultForm(dataList));
	}

	private DiscountPageResultForm buildDiscountPageResultForm(
			List<Discount> dataList) {
		DiscountPageResultForm result = new DiscountPageResultForm();
		for (Discount data : dataList) {
			DiscountPageItem item = new DiscountPageItem();
			item.setAmount(data.getAmount().toString());
			item.setBandarNoteNumber(data.getBandarNoteNumber());
			item.setExpireDate(DateUtil.getDateString(data.getExpireDate()));
			item.setProposer(data.getProposer().getName());
			item.setState(data.getState().getDescription());
			result.getItems().add(item);
		}
		return result;
	}
}
