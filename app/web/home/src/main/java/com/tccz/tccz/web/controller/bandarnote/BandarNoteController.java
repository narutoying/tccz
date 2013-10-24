/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.web.controller.bandarnote;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.tccz.tccz.core.model.BandarNote;
import com.tccz.tccz.core.model.Enterprise;
import com.tccz.tccz.core.model.FullMarginBandarNote;
import com.tccz.tccz.core.model.Money;
import com.tccz.tccz.core.model.OpenBandarNote;
import com.tccz.tccz.core.model.enums.BandarNoteType;
import com.tccz.tccz.core.model.query.BandarNoteQueryCondition;
import com.tccz.tccz.core.service.manage.BandarNoteManageService;
import com.tccz.tccz.core.service.query.BandarNoteQueryService;
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
public class BandarNoteController {

	private static final String QUERY_TYPE = "bandarnote";
	private static final String QUERY_INDEX_HTM = "/query/" + QUERY_TYPE
			+ "/index.htm";

	@Autowired
	private BandarNoteManageService bandarNoteManageService;

	@Autowired
	private BandarNoteQueryService bandarNoteQueryService;

	private static final String PREFIX = "bandarnote/";

	@RequestMapping(QUERY_INDEX_HTM)
	public String index(ModelMap modelMap) {
		List<Map<String, String>> list = BandarNoteType.toList();
		Map<String, String> element = new HashMap<String, String>();
		element.put("code", "");
		element.put("desc", "------全部类型------");
		list.add(0, element);
		modelMap.addAttribute("types", JSONSerializer.toJSON(list));
		return PREFIX + "index";
	}

	@RequestMapping(value = "/query/" + QUERY_TYPE + "/index.json", method = RequestMethod.GET)
	public void indexJson(HttpServletResponse res, ModelMap map,
			BandarNoteQueryCondition condition) {
		PageList<BandarNote> queryByCondition = bandarNoteQueryService
				.queryByCondition(condition);
		List<BandarNote> dataList = queryByCondition.getDataList();
		JSONUtil.writeBackJsonWithConfig(res,
				buildPageResultForm(dataList, queryByCondition.getPaginator()));
	}

	private PageResultForm buildPageResultForm(List<BandarNote> dataList,
			Paginator paginator) {
		PageResultForm result = new PageResultForm();
		result.setTotalCount(paginator.getItems());
		for (BandarNote data : dataList) {
			PageItem item = new PageItem();
			item.setId(data.getId());
			item.setAmount(data.getAmount().toString());
			item.setExpireDate(DateUtil.getDateString(data.getExpireDate()));
			item.setBandarNoteNumber(data.getNumber());
			item.setDrawDate(DateUtil.getDateString(data.getDrawDate()));
			item.setDrawer(data.getDrawer().getName());
			item.setType(data.getType().getDescription());
			result.getItems().add(item);
		}
		return result;
	}

	@RequestMapping("/update/" + QUERY_TYPE + "/add.htm")
	public String goAdd(ModelMap modelMap) {
		initModelMap(modelMap, OperationType.ADD, null);
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/" + QUERY_TYPE + "/add.htm", method = RequestMethod.POST)
	public String doAdd(ModelMap modelMap, BandarNoteForm form) {
		CommonResult result = bandarNoteManageService.create(buildDomain(form));
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_INDEX_HTM;
			}
		});
	}

	@RequestMapping(value = "/update/" + QUERY_TYPE + "/delete.htm")
	public String delete(ModelMap modelMap, Integer itemId) {
		CommonResult result = bandarNoteManageService.delete(itemId);
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_INDEX_HTM;
			}
		});
	}

	@RequestMapping("/update/" + QUERY_TYPE + "/modify.htm")
	public String goModify(ModelMap modelMap, Integer itemId) {
		BandarNote item = bandarNoteQueryService.queryById(itemId);
		initModelMap(modelMap, OperationType.UPDATE, item);
		return PREFIX + "one";
	}

	@RequestMapping(value = "/update/" + QUERY_TYPE + "/modify.htm", method = RequestMethod.POST)
	public String doModify(ModelMap modelMap, BandarNoteForm form) {
		CommonResult result = bandarNoteManageService.update(buildDomain(form));
		return WebUtil.goPage(modelMap, result, new WebPageCallback() {

			@Override
			public String successPage() {
				return "redirect:" + QUERY_INDEX_HTM;
			}
		});
	}

	@RequestMapping("/query/" + QUERY_TYPE + "/view.htm")
	public String view(ModelMap modelMap, Integer itemId) {
		BandarNote item = bandarNoteQueryService.queryById(itemId);
		initModelMap(modelMap, OperationType.QUERY, item);
		modelMap.addAttribute("readOnly", true);
		return PREFIX + "one";
	}

	private void initModelMap(ModelMap modelMap, OperationType operationType,
			BandarNote item) {
		modelMap.addAttribute(OperationType.OPERATION, operationType.getCode());
		modelMap.addAttribute(OperationType.OPERATION_DESC,
				operationType.getDescription());
		modelMap.addAttribute("types",
				JSONSerializer.toJSON(BandarNoteType.toList()));
		if (item != null) {
			modelMap.addAttribute("item", JSONSerializer.toJSON(item));
		}
	}

	private BandarNote buildDomain(BandarNoteForm form) {
		BandarNote result = null;
		String type = form.getType();
		BandarNoteType bandarNoteType = BandarNoteType.getByCode(type);
		if (bandarNoteType != null) {
			if (bandarNoteType == BandarNoteType.FULL_MARGIN) {
				result = new FullMarginBandarNote();
			} else if (bandarNoteType == BandarNoteType.OPEN) {
				OpenBandarNote tmp = new OpenBandarNote();
				tmp.setOpenMoney(new Money(form.getOpenMoney()));
				tmp.setCloseMoney(new Money(form.getCloseMoney()));
				result = tmp;
			}
			Integer id = form.getId();
			if (id != null) {
				result.setId(id);
			}
			result.setAmount(new Money(form.getAmount()));
			result.setDrawDate(form.getDrawDate());
			Enterprise drawer = new Enterprise();
			drawer.setIdentifier(form.getDrawerId());
			result.setDrawer(drawer);
			result.setExpireDate(form.getExpireDate());
			result.setMargin(new Money(form.getMarginMoney()));
			result.setNumber(form.getBandarNoteNumber());
			result.setType(BandarNoteType.getByCode(form.getType()));
		}
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
