/**
 * 文件名：UserAction.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.JtUser;
import com.node.service.IJtUserService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;
import com.node.util.SystemConstants;

/**
 * 类描述：用户首页登录、用户增删改查的一些操作
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:09:31
 */
@Controller
@RequestMapping("/userAction")
public class UserAction {

	@Autowired
	IJtUserService iJtUserService;

	/**
	 * 
	 * 方法描述：登录首页
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月1日 下午4:49:30
	 */
	@RequestMapping("/index")
	public String index() {

		return "index";
	}

	@RequestMapping("/checkUser")
	public void checkUser(HttpServletRequest request,
			HttpServletResponse response, String cuser, String cpassword,
			String ccode) {
		/*
		 * String code = (String) request.getSession().getAttribute("certCode");
		 * 
		 * if (StringUtils.isEmpty(code) || StringUtils.isEmpty(ccode)) {
		 * AjaxUtil.rendJson(response, false, "验证码获取失败，请刷新页面重试"); return; }
		 * 
		 * 
		 * if (!code.equalsIgnoreCase(ccode)) { AjaxUtil.rendJson(response,
		 * false, "验证码不正确"); return; }
		 */
		JtUser jtUser = iJtUserService.findByAccount(cuser);

		if (jtUser == null || !jtUser.getUserPassword().equals(cpassword)) {

			AjaxUtil.rendJson(response, false, "用户名或密码错误！");
			return;
		} else {
			request.getSession().setAttribute("jtUser", jtUser);
			AjaxUtil.rendJson(response, true, "验证通过");
		}

	}

	/**
	 * 
	 * 方法描述：登录到首页
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午9:00:35
	 */
	@RequestMapping("/loginToMain")
	public String loginToMain() {
		return "main";

	}

	/**
	 * 
	 * 方法描述：跳转到用户管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午4:32:18
	 */
	@RequestMapping("/getAll")
	public String getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		return "user/userlist";

	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午5:12:59
	 */
	@RequestMapping("/queryAllUsers")
	@ResponseBody
	public Map<String, Object> queryAllUsers(HttpServletRequest request,
			String deptId) {
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(JtUser.class);
		if (StringUtils.isBlank(deptId)) {
			hql.addEqual("userOrg", SystemConstants.ROOT_DEPTID);
		} else {
			hql.addEqual("userOrg", deptId);
		}
		hql.addOrderBy("id");
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iJtUserService.queryByHql(hql);
		return resultMap;
	}

}
