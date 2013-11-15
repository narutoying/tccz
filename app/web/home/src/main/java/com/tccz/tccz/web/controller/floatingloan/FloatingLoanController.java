/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.floatingloan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.tccz.tccz.core.model.BusinessSide;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FloatingLoan;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.Person;
import com.tccz.tccz.core.model.enums.LoanBizSideType;
import com.tccz.tccz.core.model.query.FloatingLoanQueryCondition;
import com.tccz.tccz.core.service.manage.FloatingLoanManageService;
import com.tccz.tccz.core.service.query.FloatingLoanQueryService;
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
public class FloatingLoanController {

	private static final String QUERY_TYPE = "floatingloan";
	private static final String PREFIX = "floatingloan/";
	public static final String QUERY_INDEX_HTM = "/query/" + QUERY_TYPE
			+ "/index.htm";

	@Autowired
	private FloatingLoanQueryService floatingLoanQueryService;

	@Autowired
	private FloatingLoanManageService floatingLoanManageService;

	@RequestMapping(QUERY_INDEX_HTM)
	public String index(ModelMap modelMap, String bizSideType) {
		List<Map<String, String>> list = LoanBizSideType.toList();
		modelMap.addAttribute("types", JSONSerializer.toJSON(list));
		LoanBizSideType loanBizSideType = LoanBizSideType
				.getByCode(bizSideType);
		if (loanBizSideType == null) {
			bizSideType = LoanBizSideType.CORPORATE.getCode();
		}
		modelMap.addAttribute("bizSideType", bizSideType);
		return PREFIX + "index";
	}

	@RequestMapping(value = "/query/" + QUERY_TYPE + "/index.json", method = RequestMethod.GET)
	public void indexJson(HttpServletResponse res, ModelMap map,
			FloatingLoanQueryCondition condition) {
		PageList<FloatingLoan> queryByCondition = floatingLoanQueryService
				.queryByCondition(condition);
		List<FloatingLoan> dataList = queryByCondition.getDataList();
		JSONUtil.writeBackJsonWithConfig(res,
				buildPageResultForm(dataList, queryByCondition.getPaginator()));
	}

	private PageResultForm buildPageResultForm(List<FloatingLoan> dataList,
			Paginator paginator) {
		PageResultForm result = new PageResultForm();
		result.setTotalCount(paginator.getItems());
		for (FloatingLoan data : dataList) {
			PageItem item = new PageItem();
			item.setId(data.getId());
			item.setAmount(data.getAmount().toString());
			item.setExpireDate(DateUtil.getDateString(data.getExpireDate()));
			item.setBizSideType(data.getBizSideType().getDescription());
			item.setLoanerName(data.getLoaner().getName());
			item.setReleaseDate(DateUtil.getDateString(data.getReleaseDate()));
			result.getItems().add(item);
		}
		return result;
	}

	@RequestMapping("/update/" + QUERY_TYPE + "/add.htm")
	public String goAdd(ModelMap modelMap, String bizSideType) {
		initModelMap(modelMap, OperationType.ADD, null,
				LoanBizSideType.getByCode(bizSideType));
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/" + QUERY_TYPE + "/add.htm", method = RequestMethod.POST)
	public String doAdd(ModelMap modelMap, FloatingLoanForm form) {
		final FloatingLoan domain = buildDomain(form);
		CommonResult result = floatingLoanManageService.create(domain);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl(domain.getBizSideType());
			}
		});
	}

	private String buildQueryIndexRedirectHtmUrl(LoanBizSideType bizSideType) {
		return buildQueryIndexRedirectHtmUrl(bizSideType.getCode());
	}

	private String buildQueryIndexRedirectHtmUrl(String bizSideType) {
		return "redirect:" + QUERY_INDEX_HTM + "?bizSideType=" + bizSideType;
	}

	@RequestMapping(value = "/update/" + QUERY_TYPE + "/delete.htm")
	public String delete(ModelMap modelMap, Integer itemId) {
		CommonResult result = floatingLoanManageService.delete(itemId);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_INDEX_HTM;
			}
		});
	}

	@RequestMapping("/update/" + QUERY_TYPE + "/modify.htm")
	public String goModify(ModelMap modelMap, Integer itemId) {
		FloatingLoan item = floatingLoanQueryService.queryById(itemId);
		initModelMap(modelMap, OperationType.UPDATE, item,
				item.getBizSideType());
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/" + QUERY_TYPE + "/modify.htm", method = RequestMethod.POST)
	public String doModify(ModelMap modelMap, FloatingLoanForm form) {
		final FloatingLoan domain = buildDomain(form);
		CommonResult result = floatingLoanManageService.update(domain);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return buildQueryIndexRedirectHtmUrl(domain.getBizSideType());
			}
		});
	}

	@RequestMapping("/query/" + QUERY_TYPE + "/view.htm")
	public String view(ModelMap modelMap, Integer itemId) {
		FloatingLoan item = floatingLoanQueryService.queryById(itemId);
		initModelMap(modelMap, OperationType.QUERY, item, item.getBizSideType());
		modelMap.addAttribute("readOnly", true);
		return PREFIX + "one";
	}

	private void initModelMap(ModelMap modelMap, OperationType operationType,
			FloatingLoan item, LoanBizSideType bizSideType) {
		modelMap.addAttribute(OperationType.OPERATION, operationType.getCode());
		modelMap.addAttribute(OperationType.OPERATION_DESC,
				operationType.getDescription());
		if (item != null) {
			modelMap.addAttribute("item", JSONSerializer.toJSON(item));
		}
		modelMap.addAttribute("bizSideType", bizSideType);
	}

	private FloatingLoan buildDomain(FloatingLoanForm form) {
		FloatingLoan result = new FloatingLoan();
		Integer id = form.getId();
		if (id != null) {
			result.setId(id);
		}
		result.setAmount(new Money(form.getAmount()));
		result.setExpireDate(form.getExpireDate());
		LoanBizSideType bizSideType = LoanBizSideType.getByCode(form
				.getBizSideType());
		result.setBizSideType(bizSideType);
		BusinessSide loaner = null;
		String loanerId = form.getLoanerId();
		if (bizSideType == LoanBizSideType.PRIVATE) {
			loaner = new Person();
			loaner.setIdentifier(loanerId);
		} else if (bizSideType == LoanBizSideType.CORPORATE) {
			loaner = new Enterprise();
			loaner.setIdentifier(loanerId);
		}
		result.setLoaner(loaner);
		result.setReleaseDate(form.getReleaseDate());
		result.setHasRepayed(form.isHasRepayed());
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
