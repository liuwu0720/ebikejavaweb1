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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.model.TUser;
import com.node.service.IUserService;
import com.node.util.AjaxUtil;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.ServiceUtil;

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
	IUserService iUserService;

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

		TUser tUser = iUserService.findByAccount(cuser);

		org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
		String pw = t.encodePassword(cuser, cpassword);
		if (tUser == null || !tUser.getVcPassword().equals(pw)) {
			AjaxUtil.rendJson(response, false, "用户名或密码错误！");
			return;
		} else {
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
	@RequestMapping("/getAllUsers")
	public String getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		return "user/userlist";

	}

	/**
	 * 
	 * 方法描述：查询用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:13:13
	 */
	@RequestMapping("/queryAllUsers")
	@ResponseBody
	public Map<String, Object> queryAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		TUser user = (TUser) session.getAttribute("user");
		Page p = ServiceUtil.getcurrPage(request);
		HqlHelper hql = new HqlHelper(TUser.class);
		hql.setQueryPage(p);
		Map<String, Object> resultMap = iUserService.queryAllUsers(hql);

		return resultMap;

	}

	/**
	 * 
	 * 方法描述：页面ajax请求，根据ID查询出该用户
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:15:03
	 */
	@RequestMapping("/queryUserById")
	@ResponseBody
	public TUser queryUserById(String id) {
		int userId = Integer.parseInt(id);
		System.out.println("id ----------" + userId);
		TUser tUser = iUserService.getById(userId);
		return tUser;

	}

	/**
	 * 
	 * 方法描述：新增或修改用户
	 * 
	 * @param tUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:39:28
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public void saveOrUpdate(TUser tUser, HttpServletResponse response) {
		try {
			if (StringUtils.isBlank(tUser.getVcPassword())) {
				tUser.setVcPassword("123456");
			}
			if (tUser.getId() == null) {
				org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();

				String tt = t.encodePassword(tUser.getVcPassword(),
						tUser.getVcAccount());
				tUser.setVcPassword(tt);
				iUserService.saveUser(tUser);
			} else {
				iUserService.updateUser(tUser);
			}

			AjaxUtil.rendJson(response, true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtil.rendJson(response, false, "失败！原因:" + e.getMessage());
		}
	}
}
