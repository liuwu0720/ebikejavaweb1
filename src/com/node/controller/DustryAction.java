/**
 * 文件名：DustryAction.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.DdcHyxhBase;
import com.node.model.JtUser;
import com.node.service.IInDustryService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;

/**
 * 类描述：行业协会管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午5:03:00
 */
@Controller
@RequestMapping("/industryAction")
public class DustryAction {
	@Autowired
	IInDustryService iInDustryService;

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午5:04:21
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "industry/industrys";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:18:34
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcHyxhBase.class);
		hql.addOrderBy("id");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iInDustryService.queryByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:32:29
	 */
	@RequestMapping("/del")
	public void del(HttpServletRequest request, HttpServletResponse response,
			String id) {
		try {
			long dId = Long.parseLong(id);
			iInDustryService.deleteById(dId);
			AjaxUtil.rendJson(response, true, "操作成功");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param response
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:43:44
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(HttpServletRequest request,
			HttpServletResponse response, DdcHyxhBase ddcHyxhBase) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		ddcHyxhBase.setCjr(jtUser.getUserName());
		ddcHyxhBase.setCjbm(jtUser.getUserOrg());
		ddcHyxhBase.setCjrq(new Date());
		if (ddcHyxhBase.getId() == null) {
			try {
				ddcHyxhBase.setHyxhmm("123456");
				iInDustryService.save(ddcHyxhBase);
				AjaxUtil.rendJson(response, true, "新增成功，默认密码为123456");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
			}
		} else {
			try {
				iInDustryService.update(ddcHyxhBase);
				AjaxUtil.rendJson(response, true, "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
			}
		}
	}

	/**
	 * 
	 * 方法描述：重置
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午7:08:00
	 */
	@RequestMapping("/reset")
	public void reset(HttpServletRequest request, HttpServletResponse response,
			String id) {
		long dId = Long.parseLong(id);
		try {
			DdcHyxhBase ddcHyxhBase = iInDustryService.getDdcHyxhBase(dId);
			ddcHyxhBase.setHyxhmm("123456");
			iInDustryService.update(ddcHyxhBase);
			AjaxUtil.rendJson(response, true, "新增成功，密码为123456");
		} catch (Exception e) {
			AjaxUtil.rendJson(response, false, "操作失败，系统错误!");
		}
	}
}
