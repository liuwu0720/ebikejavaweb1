/**
 * 文件名：BlackListAction.java
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

import com.node.model.DdcHmd;
import com.node.model.JtUser;
import com.node.service.IDdcHmdService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;

/**
 * 类描述：黑名单管理
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 上午10:56:02
 */
@Controller
@RequestMapping("/blackListAction")
public class BlackListAction {
	@Autowired
	IDdcHmdService iDdcHmdService;

	/**
	 * 
	 * 方法描述：页面跳转
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午10:57:39
	 */
	@RequestMapping("/getAll")
	public String getAll() {
		return "black/blacklist";
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:35:57
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request,
			String sfzhm, String jsrxm) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(DdcHmd.class);
		if (StringUtils.isNotBlank(sfzhm)) {
			hql.addLike("sfzhm", sfzhm);
		}
		if (StringUtils.isNotBlank(jsrxm)) {
			hql.addLike("jsrxm", jsrxm);
		}

		hql.addOrderBy("id");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iDdcHmdService.queryByHql(hql);
		return resultMap;
	}

	/**
	 * 
	 * 方法描述：删除
	 * 
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:39:22
	 */
	@RequestMapping("/del")
	public void del(String id, HttpServletRequest request,
			HttpServletResponse response) {
		long blackId = Long.parseLong(id);
		try {
			iDdcHmdService.deleteHmdById(blackId);
			AjaxUtil.rendJson(response, true, "操作成功");

		} catch (Exception e) {
			e.printStackTrace();
			;
			AjaxUtil.rendJson(response, false, "操作失败！系统错误");
		}
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param request
	 * @param ddcHmd
	 * @param response
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:43:38
	 */
	@RequestMapping("/saveOrUpdate")
	public void saveOrUpdate(HttpServletRequest request, DdcHmd ddcHmd,
			HttpServletResponse response) {
		JtUser jtUser = (JtUser) request.getSession().getAttribute("jtUser");
		ddcHmd.setCjrq(new Date());
		ddcHmd.setCjbm(jtUser.getUserOrg());
		ddcHmd.setCjr(jtUser.getUserName());
		if (ddcHmd.getId() == null) {
			try {
				iDdcHmdService.save(ddcHmd);
				AjaxUtil.rendJson(response, true, "新增操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "新增操作失败，系统错误");
			}
		} else {
			try {
				iDdcHmdService.update(ddcHmd);
				AjaxUtil.rendJson(response, true, "修改操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				AjaxUtil.rendJson(response, false, "修改操作失败，系统错误");
			}
		}
	}
}
