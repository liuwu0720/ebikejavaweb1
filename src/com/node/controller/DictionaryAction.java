/**
 * 文件名：DictionaryAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.DdcSjzd;
import com.node.model.JtUser;
import com.node.service.IDictionaryService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：字典管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午1:01:45
 */
@Controller
@RequestMapping("/dictionaryAction")
public class DictionaryAction {
	@Autowired
	IDictionaryService iDictionaryService;

	@RequestMapping("/getAll")
	public String getAll() {
		return "dictionary/dictionarylist";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午1:38:30
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request, String bz,
			String dmms1) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcSjzd.class);
		hql.addIn("dmlb", SystemConstants.DMLBLIMIT);
		if (StringUtils.isNotBlank(bz)) {
			hql.addLike("bz", bz);
		}
		if (StringUtils.isNotBlank(dmms1)) {
			hql.addLike("dmms1", dmms1);
		}

		hql.addOrderBy("id", "desc");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iDictionaryService.queryByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午2:13:52
	 */
	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,
			String id) {
		long dictionId = Long.parseLong(id);
		try {
			iDictionaryService.deleteById(dictionId);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}

	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(HttpServletRequest request, DdcSjzd ddcSjzd,
			HttpServletResponse response) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		ddcSjzd.setCjbm(jtUser.getUserOrg());
		ddcSjzd.setCjr(jtUser.getUserName());
		ddcSjzd.setCjrq(new Date());

		if (ddcSjzd.getId() == null) {

			try {
				iDictionaryService.save(ddcSjzd);
				AjaxUtil.rendJson(response, true, "操作成功");

			} catch (Exception e) {
				AjaxUtil.rendJson(response, false, "系统错误，操作失败!");
			}
		} else {
			try {
				iDictionaryService.update(ddcSjzd);
				AjaxUtil.rendJson(response, true, "操作成功");
			} catch (Exception e) {
				AjaxUtil.rendJson(response, false, "系统错误，操作失败!");
			}
		}

	}
}
