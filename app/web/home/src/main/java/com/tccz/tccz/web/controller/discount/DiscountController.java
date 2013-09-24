/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.discount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tccz.tccz.common.util.CommonResult;
import com.tccz.tccz.common.util.DateUtil;
import com.tccz.tccz.common.util.PageList;
import com.tccz.tccz.core.model.Discount;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.enums.DiscountState;
import com.tccz.tccz.core.model.query.DiscountQueryCondition;
import com.tccz.tccz.core.service.DiscountManageService;
import com.tccz.tccz.core.service.query.DiscountQueryService;
import com.tccz.tccz.dal.util.Paginator;
import com.tccz.tccz.web.enums.OperationType;
import com.tccz.tccz.web.util.JSONUtil;
import com.tccz.tccz.web.util.WebPageCallback;
import com.tccz.tccz.web.util.WebUtil;

/**
 * 贴现
 * 
 * @author narutoying09@gmail.com
 * @version $Id: DiscountController.java, v 0.1 2013-9-17 下午4:48:03
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class DiscountController {

	private static final String QUERY_DISCOUNT_INDEX_HTM = "/query/discount/index.htm";

	@Autowired
	private DiscountQueryService discountQueryService;

	@Autowired
	private DiscountManageService discountManageService;

	private static final String PREFIX = "discount/";

	@RequestMapping(QUERY_DISCOUNT_INDEX_HTM)
	public String index(ModelMap modelMap, DiscountQueryCondition condition) {
		return PREFIX + "index";
	}

	@RequestMapping(value = "/query/discount/index.json", method = RequestMethod.GET)
	public void indexJson(HttpServletResponse res, ModelMap map,
			DiscountQueryCondition condition) {
		PageList<Discount> queryByCondition = discountQueryService
				.queryByCondition(condition);
		List<Discount> dataList = queryByCondition.getDataList();
		JSONUtil.writeBackJsonWithConfig(
				res,
				buildDiscountPageResultForm(dataList,
						queryByCondition.getPaginator()));
	}

	private DiscountPageResultForm buildDiscountPageResultForm(
			List<Discount> dataList, Paginator paginator) {
		DiscountPageResultForm result = new DiscountPageResultForm();
		result.setTotalCount(paginator.getItems());
		for (Discount data : dataList) {
			DiscountPageItem item = new DiscountPageItem();
			item.setId(data.getId());
			item.setAmount(data.getAmount().toString());
			item.setBandarNoteNumber(data.getBandarNoteNumber());
			item.setExpireDate(DateUtil.getDateString(data.getExpireDate()));
			Enterprise proposer = data.getProposer();
			if (proposer != null) {
				item.setProposer(proposer.getName());
			}
			item.setState(data.getState().getDescription());
			result.getItems().add(item);
		}
		return result;
	}

	@RequestMapping("/update/discount/add.htm")
	public String goAdd(ModelMap modelMap) {
		initModelMap(modelMap, OperationType.ADD);
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/discount/add.htm", method = RequestMethod.POST)
	public String doAdd(ModelMap modelMap, DiscountForm form) {
		CommonResult result = discountManageService
				.createDiscount(buildDiscount(form));
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_DISCOUNT_INDEX_HTM;
			}
		});
	}

	@RequestMapping(value = "/update/discount/delete.htm")
	public String delete(ModelMap modelMap, Integer itemId) {
		CommonResult result = discountManageService.deleteDiscount(itemId);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_DISCOUNT_INDEX_HTM;
			}
		});
	}

	@RequestMapping("/update/discount/modify.htm")
	public String goModify(ModelMap modelMap, Integer itemId) {
		initModelMap(modelMap, OperationType.UPDATE);
		modelMap.addAttribute("discount",
				discountQueryService.queryById(itemId));
		modelMap.addAttribute("itemId", itemId);
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/discount/modify.htm", method = RequestMethod.POST)
	public String doModify(ModelMap modelMap, DiscountForm form) {
		CommonResult result = discountManageService
				.updateDiscount(buildDiscount(form));
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_DISCOUNT_INDEX_HTM;
			}
		});
	}

	@RequestMapping("/query/discount/view.htm")
	public String view(ModelMap modelMap, Integer itemId) {
		initModelMap(modelMap, OperationType.QUERY);
		modelMap.addAttribute("discount",
				discountQueryService.queryById(itemId));
		modelMap.addAttribute("readOnly", true);
		return PREFIX + "one";
	}

	private void initModelMap(ModelMap modelMap, OperationType operationType) {
		modelMap.addAttribute(OperationType.OPERATION, operationType.getCode());
		modelMap.addAttribute(OperationType.OPERATION_DESC,
				operationType.getDescription());
		modelMap.addAttribute("states",
				JSONSerializer.toJSON(DiscountState.toList()));
	}

	private Discount buildDiscount(DiscountForm form) {
		Discount result = new Discount();
		Integer id = form.getId();
		if (id != null) {
			result.setId(id);
		}
		result.setAmount(new Money(form.getAmount()));
		result.setBandarNoteNumber(form.getBandarNoteNumber());
		result.setExpireDate(form.getExpireDate());
		Enterprise proposer = new Enterprise();
		proposer.setId(form.getProposerId());
		result.setProposer(proposer);
		result.setState(DiscountState.getByCode(form.getState()));
		return result;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}
